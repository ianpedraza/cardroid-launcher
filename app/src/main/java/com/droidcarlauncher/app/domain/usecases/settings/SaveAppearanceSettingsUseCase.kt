/*
 * SaveSettingsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 19:38
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.settings

import com.droidcarlauncher.app.data.repository.settings.SettingsRepository
import com.droidcarlauncher.app.domain.model.settings.appearance.AppearanceSettings
import javax.inject.Inject

class SaveAppearanceSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {

    suspend operator fun invoke(settings: AppearanceSettings) {
        repository.saveAppearanceSettings(settings)
    }
}
