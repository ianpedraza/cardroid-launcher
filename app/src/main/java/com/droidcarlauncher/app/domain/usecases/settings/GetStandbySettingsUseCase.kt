/*
 * GetStandbySettingsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 16:57
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.settings

import com.droidcarlauncher.app.data.repository.settings.SettingsRepository
import com.droidcarlauncher.app.domain.model.settings.standby.StandbySettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStandbySettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<StandbySettings> = repository.getStandbySettings()
}
