/*
 * IconStorageManager.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 21/9/23 12:54
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.iconpacks.manager.storage

import android.graphics.drawable.Drawable
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon

interface IconStorageManager {
    fun clear(): Boolean
    fun save(customIcon: CustomIcon): Boolean
    fun remove(iconName: String): Boolean
    fun getIcon(iconName: String): Drawable?
    fun isSystemStockIcon(iconName: String): Boolean
}
