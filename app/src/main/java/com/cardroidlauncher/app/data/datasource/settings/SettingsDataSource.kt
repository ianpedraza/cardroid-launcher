/*
 * SettingsDataSource.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 19:05
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.datasource.settings

import com.cardroidlauncher.app.domain.model.settings.appearance.AppearanceSettings
import com.cardroidlauncher.app.domain.model.settings.general.GeneralSettings
import com.cardroidlauncher.app.domain.model.settings.standby.StandbySettings
import com.cardroidlauncher.app.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    suspend fun saveAppearanceSettings(settings: AppearanceSettings)
    suspend fun getAppearanceSettings(): Flow<AppearanceSettings>
    suspend fun saveStandbySettings(settings: StandbySettings)
    suspend fun getStandbySettings(): Flow<StandbySettings>
    suspend fun saveWallpaperSettings(settings: WallpaperSettings)
    suspend fun getWallpaperSettings(): Flow<WallpaperSettings>
    suspend fun saveGeneralSettings(settings: GeneralSettings)
    suspend fun getGeneralSettings(): Flow<GeneralSettings>
}
