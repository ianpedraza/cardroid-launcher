/*
 * DefaultApplicationsRepository.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 7/9/23 12:09
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.repository.applications

import com.droidcarlauncher.app.data.datasource.applications.ApplicationsDataSource
import com.droidcarlauncher.app.data.framework.iconpacks.manager.cache.CustomIconsCache
import com.droidcarlauncher.app.data.framework.iconpacks.manager.storage.IconStorageManager
import com.droidcarlauncher.app.di.IoDispatcher
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import com.droidcarlauncher.app.domain.utils.extension.AppModelExtension.move
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultApplicationsRepository @Inject constructor(
    private val dataSource: ApplicationsDataSource,
    private val iconStorageManager: IconStorageManager,
    private val customIconsCache: CustomIconsCache,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ApplicationsRepository {

    override suspend fun saveSystemApplications(apps: List<AppModel>): Unit =
        withContext(dispatcher) {
            try {
                val savedApps = dataSource.getApplications()
                val savedAppsPackageNames = savedApps.map { it.packageName }
                var lastPosition =
                    savedApps.maxByOrNull { it.position }?.position ?: INITIAL_POSITION

                val appsToInsert = apps.mapNotNull {
                    if (it.packageName !in savedAppsPackageNames) {
                        it.copy(position = ++lastPosition)
                    } else {
                        null
                    }
                }
                dataSource.insert(appsToInsert)
            } catch (e: Exception) {
                // TODO("Implement Crashlytics")
            }
        }

    override suspend fun getAllApplications(): Flow<List<AppModel>> {
        return dataSource.observeApplications()
    }

    override suspend fun getVisibleApplication(): Flow<List<AppModel>> {
        return dataSource.observeApplications().map { apps -> apps.filter { it.hidden.not() } }
    }

    override suspend fun moveApplications(originIndex: Int, targetIndex: Int): Unit =
        withContext(dispatcher) {
            try {
                val apps = dataSource.getApplications()
                val appsToUpdate = apps.move(originIndex, targetIndex)
                dataSource.update(appsToUpdate)
            } catch (e: Exception) {
                // TODO("Implement Crashlytics")
            }
        }

    override suspend fun removeApplications(apps: List<AppModel>): Unit = withContext(dispatcher) {
        try {
            dataSource.remove(apps)
        } catch (e: Exception) {
            // TODO("Implement Crashlytics")
        }
    }

    override suspend fun hideApplications(apps: List<AppModel>): Unit = withContext(dispatcher) {
        try {
            val hiddenApps = apps.map { it.copy(hidden = true) }
            dataSource.update(hiddenApps)
        } catch (e: Exception) {
            // TODO("Implement Crashlytics")
        }
    }

    override suspend fun unhideApplications(apps: List<AppModel>): Unit = withContext(dispatcher) {
        try {
            val unhiddenApps = apps.map { it.copy(hidden = false) }
            dataSource.update(unhiddenApps)
        } catch (e: Exception) {
            // TODO("Implement Crashlytics")
        }
    }

    override suspend fun editApplication(app: AppModel, customIcon: CustomIcon?): Unit =
        withContext(dispatcher) {
            try {
                dataSource.getApplication(app.id)?.let { currentApplication ->
                    if (shouldRemovePreviousIcon(currentApplication, customIcon)) {
                        currentApplication.customIconName?.let {
                            iconStorageManager.remove(it)
                            customIconsCache.removeCached(it)
                        }
                    }

                    var appToUpdate = currentApplication.copy(
                        customLabel = app.customLabel,
                        customIconName = app.customIconName
                    )

                    customIcon?.let {
                        val iconName = it.iconName

                        if (iconStorageManager.isSystemStockIcon(iconName)) {
                            iconStorageManager.remove(iconName)
                            customIconsCache.removeCached(iconName)
                        }

                        if (!iconStorageManager.save(it)) {
                            appToUpdate = appToUpdate.copy(
                                customIconName = currentApplication.customIconName
                            )
                        }
                    }

                    dataSource.update(listOf(appToUpdate))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO("Implement Crashlytics")
            }
        }

    override suspend fun resetIcons(): Unit = withContext(dispatcher) {
        customIconsCache.clear()
        iconStorageManager.clear()

        val appsToUpdate = dataSource.getApplications().map {
            it.copy(
                customIconName = null,
                customLabel = null
            )
        }
        dataSource.update(appsToUpdate)
    }

    private suspend fun shouldRemovePreviousIcon(
        app: AppModel,
        customIcon: CustomIcon?
    ): Boolean {
        val apps = dataSource.getApplications().filter { it.id != app.id }
        return app.customIconName != null && app.customIconName != customIcon?.iconName
                && apps.any { it.customIconName == app.customIconName }.not()
    }

    companion object {
        private const val INITIAL_POSITION = -1
    }
}
