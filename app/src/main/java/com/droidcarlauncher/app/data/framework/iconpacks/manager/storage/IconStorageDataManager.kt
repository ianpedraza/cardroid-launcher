/*
 * IconStorageDataManager.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 21/9/23 12:54
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.iconpacks.manager.storage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.droidcarlauncher.app.data.framework.iconpacks.manager.cache.CustomIconsDataCache
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import com.droidcarlauncher.app.presentation.main.utils.Utils.getApplicationIcon
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class IconStorageDataManager @Inject constructor(
    @ApplicationContext private val context: Context
) : IconStorageManager {

    override fun clear(): Boolean {
        val file = iconsDirectory

        return try {
            if (file.exists()) {
                file.delete()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun save(customIcon: CustomIcon): Boolean {
        if (isSystemStockIcon(customIcon.iconName)) {
            return true
        }

        val bitmap = drawableToBitmap(customIcon.drawable)
        CustomIconsDataCache.saveCached(customIcon)
        return saveBitmapToInternalStorage(bitmap, customIcon.iconName)
    }

    override fun remove(iconName: String): Boolean {
        return removeBitmapFromInternalStorage(iconName)
    }

    override fun getIcon(iconName: String): Drawable? {
        if (isSystemStockIcon(iconName)) {
            val iconPackageName = iconName.removePrefix("${context.packageName}_")
            return context.getApplicationIcon(iconPackageName)
        } else {
            CustomIconsDataCache.getCached(iconName)?.let { customIcon ->
                return customIcon.drawable
            }
        }

        return getBitmapFromInternalStorage(iconName)?.let { bitmap ->
            bitmapToDrawable(bitmap)
        }
    }

    override fun isSystemStockIcon(iconName: String): Boolean {
        return iconName.startsWith(context.packageName)
    }

    private fun removeBitmapFromInternalStorage(fileName: String): Boolean {
        val file = File(iconsDirectory, fileName)

        return try {
            if (file.exists()) {
                file.delete()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun getBitmapFromInternalStorage(fileName: String): Bitmap? {
        val file = File(iconsDirectory, fileName)

        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }

    private fun saveBitmapToInternalStorage(bitmap: Bitmap, fileName: String): Boolean {
        val file = File(iconsDirectory, fileName)

        if (file.exists().not()) {
            return try {
                FileOutputStream(file).use { fileOutputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
                    true
                }
            } catch (e: Exception) {
                // NO-OP
                false
            }
        }

        return true
    }

    private fun bitmapToDrawable(bitmap: Bitmap): Drawable {
        return BitmapDrawable(context.resources, bitmap)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    private val iconsDirectory: File get() = context.getDir("icons", Context.MODE_PRIVATE)
}
