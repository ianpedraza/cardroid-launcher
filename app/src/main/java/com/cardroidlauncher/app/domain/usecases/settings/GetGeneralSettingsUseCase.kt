/*
 * GetGeneralSettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 13:13
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.general.GeneralSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGeneralSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<GeneralSettings> = repository.getGeneralSettings()
}
