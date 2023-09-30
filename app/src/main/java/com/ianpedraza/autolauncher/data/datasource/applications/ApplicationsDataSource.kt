/*
 * ApplicationsDataSource.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 7/9/23 13:24
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.datasource.applications

import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import kotlinx.coroutines.flow.Flow

interface ApplicationsDataSource {
    fun observeApplications(): Flow<List<AppModel>>
    suspend fun getApplications(): List<AppModel>
    suspend fun getApplication(id: Long): AppModel?
    suspend fun insert(apps: List<AppModel>)
    suspend fun update(apps: List<AppModel>)
    suspend fun remove(apps: List<AppModel>)
}
