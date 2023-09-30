/*
 * RoomApplicationsDataSource.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 19:18
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.applications

import com.droidcarlauncher.app.data.datasource.applications.ApplicationsDataSource
import com.droidcarlauncher.app.data.framework.applications.room.AppsDao
import com.droidcarlauncher.app.di.IoDispatcher
import com.droidcarlauncher.app.domain.mapper.AppMappers.toAppEntity
import com.droidcarlauncher.app.domain.mapper.AppMappers.toAppModel
import com.droidcarlauncher.app.domain.model.applications.AppModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomApplicationsDataSource @Inject constructor(
    private val appsDao: AppsDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ApplicationsDataSource {

    override fun observeApplications(): Flow<List<AppModel>> {
        return appsDao.observeApps().map { it.toAppModel() }
    }

    override suspend fun getApplications(): List<AppModel> = withContext(dispatcher) {
        appsDao.getAll().toAppModel()
    }

    override suspend fun getApplication(id: Long): AppModel? = withContext(dispatcher) {
        appsDao.getApp(id)?.toAppModel()
    }

    override suspend fun insert(apps: List<AppModel>): Unit = withContext(dispatcher) {
        appsDao.insert(apps.toAppEntity())
    }

    override suspend fun update(apps: List<AppModel>): Unit = withContext(dispatcher) {
        appsDao.update(apps.toAppEntity())
    }

    override suspend fun remove(apps: List<AppModel>): Unit = withContext(dispatcher) {
        appsDao.remove(apps.toAppEntity())
    }

}

