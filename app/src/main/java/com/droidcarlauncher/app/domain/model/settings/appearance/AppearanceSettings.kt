/*
 * AppearanceSettings.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 11:44
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.appearance

import com.droidcarlauncher.app.domain.model.settings.appearance.darkheme.DarkThemeMode

data class AppearanceSettings(
    val darkThemeMode: DarkThemeMode = DarkThemeMode.default,
    val iconPack: String? = null
)
