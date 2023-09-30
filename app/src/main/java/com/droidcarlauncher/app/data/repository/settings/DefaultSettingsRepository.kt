/*
 * DefaultSettingsRepository.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 19:36
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.repository.settings

import com.droidcarlauncher.app.data.datasource.settings.SettingsDataSource
import com.droidcarlauncher.app.di.IoDispatcher
import com.droidcarlauncher.app.domain.model.settings.appearance.AppearanceSettings
import com.droidcarlauncher.app.domain.model.settings.general.GeneralSettings
import com.droidcarlauncher.app.domain.model.settings.standby.StandbySettings
import com.droidcarlauncher.app.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultSettingsRepository @Inject constructor(
    private val dataSource: SettingsDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SettingsRepository {

    override suspend fun saveAppearanceSettings(settings: AppearanceSettings): Unit =
        withContext(dispatcher) {
            try {
                dataSource.saveAppearanceSettings(settings)
            } catch (e: Exception) {
                // TODO("Implement analytics")
            }
        }

    override suspend fun getAppearanceSettings(): Flow<AppearanceSettings> =
        withContext(dispatcher) {
            dataSource.getAppearanceSettings()
        }

    override suspend fun saveStandbySettings(settings: StandbySettings): Unit =
        withContext(dispatcher) {
            try {
                dataSource.saveStandbySettings(settings)
            } catch (e: Exception) {
                // TODO("Implement analytics")
            }
        }

    override suspend fun getStandbySettings(): Flow<StandbySettings> = withContext(dispatcher) {
        dataSource.getStandbySettings()
    }

    override suspend fun saveWallpaperSettings(settings: WallpaperSettings): Unit =
        withContext(dispatcher) {
            try {
                dataSource.saveWallpaperSettings(settings)
            } catch (e: Exception) {
                // TODO("Implement analytics")
            }
        }

    override suspend fun getWallpaperSettings(): Flow<WallpaperSettings> = withContext(dispatcher) {
        dataSource.getWallpaperSettings()
    }

    override suspend fun saveGeneralSettings(settings: GeneralSettings): Unit =
        withContext(dispatcher) {
            try {
                dataSource.saveGeneralSettings(settings)
            } catch (e: Exception) {
                // TODO("Implement analytics")
                e.printStackTrace()
            }
        }

    override suspend fun getGeneralSettings(): Flow<GeneralSettings> {
        return dataSource.getGeneralSettings()
    }
}
