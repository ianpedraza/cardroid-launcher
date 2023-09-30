/*
 * SaveWallpaperSettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 14/9/23 20:07
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.wallpaper.WallpaperSettings
import javax.inject.Inject

class SaveWallpaperSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(settings: WallpaperSettings) {
        repository.saveWallpaperSettings(settings)
    }
}
