/*
 * SaveGeneralSettingsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 16/9/23 13:11
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.settings

import com.droidcarlauncher.app.data.repository.settings.SettingsRepository
import com.droidcarlauncher.app.domain.model.settings.general.GeneralSettings
import javax.inject.Inject

class SaveGeneralSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(settings: GeneralSettings) {
        repository.saveGeneralSettings(settings)
    }
}
