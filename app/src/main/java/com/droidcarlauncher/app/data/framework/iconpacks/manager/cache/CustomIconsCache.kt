/*
 * CustomIconsCache.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 21/9/23 15:29
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.iconpacks.manager.cache

import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon

interface CustomIconsCache {
    fun saveCached(icon: CustomIcon)
    fun removeCached(iconName: String)
    fun clear()
    fun clear(packageName: String)
    fun getCached(iconName: String): CustomIcon?
}
