/*
 * DefaultIconPacksRepository.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 12:13
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.repository.iconpacks

import com.cardroidlauncher.app.data.datasource.iconpacks.IconPacksDataSource
import com.cardroidlauncher.app.di.IoDispatcher
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.model.iconpacks.IconPackModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultIconPacksRepository @Inject constructor(
    private val dataSource: IconPacksDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : IconPacksRepository {

    override suspend fun fetchIconPacks(): List<IconPackModel> =
        withContext(dispatcher) {
            try {
                dataSource.fetchIconPacks(force = true)
            } catch (e: Exception) {
                // TODO("Implement Crashlytics")
                emptyList()
            }
        }

    override suspend fun getSuggestedIcons(app: AppModel): List<CustomIcon> =
        withContext(dispatcher) {
            try {
                dataSource.getSuggestedIcons(app)
            } catch (e: Exception) {
                // TODO("Implement Crashlytics")
                emptyList()
            }
        }

    override suspend fun getAllIcons(iconPack: IconPackModel): List<CustomIcon> =
        withContext(dispatcher) {
            try {
                dataSource.getAllIcons(iconPack)
            } catch (e: Exception) {
                // TODO("Implement Crashlytics")
                emptyList()
            }
        }

    override suspend fun getIconPack(packageName: String): IconPackModel? =
        withContext(dispatcher) {
            try {
                dataSource.getIconPack(packageName)
            } catch (e: Exception) {
                // TODO("Implement Crashlytics")
                null
            }
        }


}
