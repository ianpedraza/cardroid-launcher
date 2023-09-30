/*
 * IconsPackManager.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 19/9/23 18:00
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.datasource.iconpacks

import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel

interface IconPacksDataSource {
    suspend fun fetchIconPacks(force: Boolean = false): List<IconPackModel>
    suspend fun getSuggestedIcons(app: AppModel): List<CustomIcon>
    suspend fun getAllIcons(iconPack: IconPackModel): List<CustomIcon>
    suspend fun getIconPack(packageName: String): IconPackModel?
}
