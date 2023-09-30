/*
 * GetWallpaperSettingsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 14/9/23 20:08
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.settings

import com.droidcarlauncher.app.data.repository.settings.SettingsRepository
import com.droidcarlauncher.app.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWallpaperSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<WallpaperSettings> = repository.getWallpaperSettings()
}
