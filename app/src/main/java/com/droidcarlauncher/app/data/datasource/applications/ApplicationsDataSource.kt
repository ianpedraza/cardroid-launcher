/*
 * ApplicationsDataSource.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 7/9/23 13:24
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.datasource.applications

import com.droidcarlauncher.app.domain.model.applications.AppModel
import kotlinx.coroutines.flow.Flow

interface ApplicationsDataSource {
    fun observeApplications(): Flow<List<AppModel>>
    suspend fun getApplications(): List<AppModel>
    suspend fun getApplication(id: Long): AppModel?
    suspend fun insert(apps: List<AppModel>)
    suspend fun update(apps: List<AppModel>)
    suspend fun remove(apps: List<AppModel>)
}
