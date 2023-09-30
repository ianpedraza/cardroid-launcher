/*
 * SaveStandbySettingsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 16:57
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.settings

import com.ianpedraza.autolauncher.data.repository.settings.SettingsRepository
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbySettings
import javax.inject.Inject

class SaveStandbySettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {

    suspend operator fun invoke(settings: StandbySettings) {
        repository.saveStandbySettings(settings)
    }
}
