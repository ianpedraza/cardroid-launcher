/*
 * IconsPackManager.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 19/9/23 18:00
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.datasource.iconpacks

import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import com.droidcarlauncher.app.domain.model.iconpacks.IconPackModel

interface IconPacksDataSource {
    suspend fun fetchIconPacks(force: Boolean = false): List<IconPackModel>
    suspend fun getSuggestedIcons(app: AppModel): List<CustomIcon>
    suspend fun getAllIcons(iconPack: IconPackModel): List<CustomIcon>
    suspend fun getIconPack(packageName: String): IconPackModel?
}
