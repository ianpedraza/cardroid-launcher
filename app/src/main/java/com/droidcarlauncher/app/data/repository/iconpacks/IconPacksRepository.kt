/*
 * IconPacksRepository.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 15:08
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.repository.iconpacks

import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import com.droidcarlauncher.app.domain.model.iconpacks.IconPackModel

interface IconPacksRepository {
    suspend fun fetchIconPacks(): List<IconPackModel>
    suspend fun getSuggestedIcons(app: AppModel): List<CustomIcon>
    suspend fun getAllIcons(iconPack: IconPackModel): List<CustomIcon>
    suspend fun getIconPack(packageName: String): IconPackModel?
}
