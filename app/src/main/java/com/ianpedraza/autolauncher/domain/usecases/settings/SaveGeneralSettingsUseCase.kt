/*
 * SaveGeneralSettingsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 16/9/23 13:11
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.settings

import com.ianpedraza.autolauncher.data.repository.settings.SettingsRepository
import com.ianpedraza.autolauncher.domain.model.settings.general.GeneralSettings
import javax.inject.Inject

class SaveGeneralSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(settings: GeneralSettings) {
        repository.saveGeneralSettings(settings)
    }
}
