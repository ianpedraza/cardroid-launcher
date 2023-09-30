/*
 * CustomIconsDataCache.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 21/9/23 12:53
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.iconpacks.manager.cache

import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon

object CustomIconsDataCache : CustomIconsCache {

    private val iconsMap: MutableMap<String, CustomIcon> = mutableMapOf()

    override fun saveCached(icon: CustomIcon) {
        if (!iconsMap.containsKey(icon.iconName)) {
            iconsMap[icon.iconName] = icon
        }
    }

    override fun removeCached(iconName: String) {
        if (iconsMap.containsKey(iconName)) {
            iconsMap.remove(iconName)
        }
    }

    override fun clear() {
        iconsMap.clear()
    }

    override fun clear(packageName: String) {
        iconsMap.keys
            .filter { it.startsWith(packageName) }
            .forEach { iconsMap.remove(it) }
    }

    override fun getCached(iconName: String): CustomIcon? {
        return iconsMap[iconName]
    }
}
