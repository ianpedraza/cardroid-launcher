/*
 * ApplicationsRepository.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 7/9/23 13:24
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.repository.applications

import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import kotlinx.coroutines.flow.Flow

interface ApplicationsRepository {
    suspend fun saveSystemApplications(apps: List<AppModel>)
    suspend fun getAllApplications(): Flow<List<AppModel>>
    suspend fun getVisibleApplication(): Flow<List<AppModel>>
    suspend fun moveApplications(originIndex: Int, targetIndex: Int)
    suspend fun removeApplications(apps: List<AppModel>)
    suspend fun hideApplications(apps: List<AppModel>)
    suspend fun unhideApplications(apps: List<AppModel>)
    suspend fun editApplication(app: AppModel, customIcon: CustomIcon? = null)
    suspend fun resetIcons()
}
