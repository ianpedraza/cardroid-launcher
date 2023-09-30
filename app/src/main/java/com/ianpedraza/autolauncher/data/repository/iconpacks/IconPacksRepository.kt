/*
 * IconPacksRepository.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 15:08
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.repository.iconpacks

import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel

interface IconPacksRepository {
    suspend fun fetchIconPacks(): List<IconPackModel>
    suspend fun getSuggestedIcons(app: AppModel): List<CustomIcon>
    suspend fun getAllIcons(iconPack: IconPackModel): List<CustomIcon>
    suspend fun getIconPack(packageName: String): IconPackModel?
}
