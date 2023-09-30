/*
 * GetSettingsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 19:39
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.settings

import com.cardroidlauncher.app.data.repository.settings.SettingsRepository
import com.cardroidlauncher.app.domain.model.settings.appearance.AppearanceSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppearanceSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<AppearanceSettings> = repository.getAppearanceSettings()
}
