/*
 * CustomIconsDataCache.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 21/9/23 12:53
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.iconpacks.manager.cache

import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon

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
