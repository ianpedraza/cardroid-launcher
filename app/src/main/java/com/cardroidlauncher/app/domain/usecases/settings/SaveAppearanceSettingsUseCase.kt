/*
 * SaveSettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 19:38
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.appearance.AppearanceSettings
import javax.inject.Inject

class SaveAppearanceSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {

    suspend operator fun invoke(settings: AppearanceSettings) {
        repository.saveAppearanceSettings(settings)
    }
}
