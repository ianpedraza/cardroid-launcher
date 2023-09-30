/*
 * SaveStandbySettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 16:57
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.standby.StandbySettings
import javax.inject.Inject

class SaveStandbySettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {

    suspend operator fun invoke(settings: StandbySettings) {
        repository.saveStandbySettings(settings)
    }
}
