/*
 * GetSettingsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 19:39
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.settings

import com.droidcarlauncher.app.data.repository.settings.SettingsRepository
import com.droidcarlauncher.app.domain.model.settings.appearance.AppearanceSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppearanceSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<AppearanceSettings> = repository.getAppearanceSettings()
}
