/*
 * GetWallpaperSettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 14/9/23 20:08
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWallpaperSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<WallpaperSettings> = repository.getWallpaperSettings()
}
