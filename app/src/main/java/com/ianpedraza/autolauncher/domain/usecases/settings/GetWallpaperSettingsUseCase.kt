/*
 * GetWallpaperSettingsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 14/9/23 20:08
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.settings

import com.ianpedraza.autolauncher.data.repository.settings.SettingsRepository
import com.ianpedraza.autolauncher.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWallpaperSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<WallpaperSettings> = repository.getWallpaperSettings()
}
