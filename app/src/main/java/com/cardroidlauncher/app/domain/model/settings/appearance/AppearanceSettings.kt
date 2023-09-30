/*
 * AppearanceSettings.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 11:44
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.appearance

import com.cardroidlauncher.app.domain.model.settings.appearance.darkheme.DarkThemeMode

data class AppearanceSettings(
    val darkThemeMode: DarkThemeMode = DarkThemeMode.default,
    val iconPack: String? = null
)
