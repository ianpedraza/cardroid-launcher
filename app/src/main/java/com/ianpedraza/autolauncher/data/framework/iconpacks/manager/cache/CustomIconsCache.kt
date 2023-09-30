/*
 * CustomIconsCache.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 21/9/23 15:29
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.iconpacks.manager.cache

import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon

interface CustomIconsCache {
    fun saveCached(icon: CustomIcon)
    fun removeCached(iconName: String)
    fun clear()
    fun clear(packageName: String)
    fun getCached(iconName: String): CustomIcon?
}
