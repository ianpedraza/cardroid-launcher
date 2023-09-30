/*
 * IconPackDataManager.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 22/9/23 14:20
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.framework.iconpacks.manager.iconpack

import android.content.Context
import android.graphics.drawable.Drawable
import com.cardroidlauncher.app.data.framework.iconpacks.IconPack
import com.cardroidlauncher.app.data.framework.iconpacks.manager.cache.CustomIconsDataCache
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.utils.Constants.EMPTY_STRING

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
