/*
 * IconStorageManager.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 21/9/23 12:54
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.iconpacks.manager.storage

import android.graphics.drawable.Drawable
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon

interface IconStorageManager {
    fun clear(): Boolean
    fun save(customIcon: CustomIcon): Boolean
    fun remove(iconName: String): Boolean
    fun getIcon(iconName: String): Drawable?
    fun isSystemStockIcon(iconName: String): Boolean
}
