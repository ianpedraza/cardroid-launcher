/*
 * GetSettingsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 19:39
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.settings

import com.ianpedraza.autolauncher.data.repository.settings.SettingsRepository
import com.ianpedraza.autolauncher.domain.model.settings.appearance.AppearanceSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppearanceSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<AppearanceSettings> = repository.getAppearanceSettings()
}
