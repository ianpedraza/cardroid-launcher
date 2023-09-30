/*
 * GetGeneralSettingsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 16/9/23 13:13
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.settings

import com.ianpedraza.autolauncher.data.repository.settings.SettingsRepository
import com.ianpedraza.autolauncher.domain.model.settings.general.GeneralSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGeneralSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<GeneralSettings> = repository.getGeneralSettings()
}
