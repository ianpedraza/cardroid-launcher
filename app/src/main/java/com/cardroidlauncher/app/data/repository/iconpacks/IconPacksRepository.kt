/*
 * IconPacksRepository.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 15:08
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.repository.iconpacks

import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.model.iconpacks.IconPackModel

interface IconPacksRepository {
    suspend fun fetchIconPacks(): List<IconPackModel>
    suspend fun getSuggestedIcons(app: AppModel): List<CustomIcon>
    suspend fun getAllIcons(iconPack: IconPackModel): List<CustomIcon>
    suspend fun getIconPack(packageName: String): IconPackModel?
}
