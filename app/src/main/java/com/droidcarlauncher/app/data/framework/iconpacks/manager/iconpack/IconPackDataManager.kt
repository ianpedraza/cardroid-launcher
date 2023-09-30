/*
 * IconPackDataManager.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 22/9/23 14:20
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.iconpacks.manager.iconpack

import android.content.Context
import android.graphics.drawable.Drawable
import com.droidcarlauncher.app.data.framework.iconpacks.IconPack
import com.droidcarlauncher.app.data.framework.iconpacks.manager.cache.CustomIconsDataCache
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.utils.Constants.EMPTY_STRING

object IconPackDataManager : IconPackManager {

    private var currentIconPack: IconPack? = null

    override fun loadIconPack(context: Context, packageName: String?) {
        currentIconPack?.let {
            CustomIconsDataCache.clear(it.packageName)
        }

        currentIconPack = if (packageName != null) {
            IconPack(packageName, EMPTY_STRING, EMPTY_STRING).also {
                it.load(context)
            }
        } else {
            null
        }
    }

    override fun getIcon(context: Context, app: AppModel): Drawable? {
        return currentIconPack?.let { iconPack ->
            val icons = iconPack.getSuggestedIcons(context, app.packageName)
            if (icons.isNotEmpty()) icons.first().drawable else null
        }
    }

}
