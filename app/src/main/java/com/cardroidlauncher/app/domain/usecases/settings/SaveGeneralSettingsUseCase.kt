/*
 * SaveGeneralSettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 13:11
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.general.GeneralSettings
import javax.inject.Inject

class SaveGeneralSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(settings: GeneralSettings) {
        repository.saveGeneralSettings(settings)
    }
}
