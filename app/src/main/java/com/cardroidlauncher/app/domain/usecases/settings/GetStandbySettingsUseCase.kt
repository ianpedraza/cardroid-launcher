/*
 * GetStandbySettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 16:57
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.standby.StandbySettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStandbySettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<StandbySettings> = repository.getStandbySettings()
}
