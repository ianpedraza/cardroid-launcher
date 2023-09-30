/*
 * SaveStandbySettingsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 16:57
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.settings

import com.droidcarlauncher.app.data.repository.settings.SettingsRepository
import com.droidcarlauncher.app.domain.model.settings.standby.StandbySettings
import javax.inject.Inject

class SaveStandbySettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {

    suspend operator fun invoke(settings: StandbySettings) {
        repository.saveStandbySettings(settings)
    }
}
