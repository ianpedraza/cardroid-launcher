/*
 * SettingsRepository.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 19:35
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.repository.settings

import com.ianpedraza.autolauncher.domain.model.settings.appearance.AppearanceSettings
import com.ianpedraza.autolauncher.domain.model.settings.general.GeneralSettings
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbySettings
import com.ianpedraza.autolauncher.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun saveAppearanceSettings(settings: AppearanceSettings)
    suspend fun getAppearanceSettings(): Flow<AppearanceSettings>
    suspend fun saveStandbySettings(settings: StandbySettings)
    suspend fun getStandbySettings(): Flow<StandbySettings>
    suspend fun saveWallpaperSettings(settings: WallpaperSettings)
    suspend fun getWallpaperSettings(): Flow<WallpaperSettings>
    suspend fun saveGeneralSettings(settings: GeneralSettings)
    suspend fun getGeneralSettings(): Flow<GeneralSettings>
}
