/*
 * StandbyModeSettings.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 11:44
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.standby

data class StandbySettings(
    val enabled: Boolean = true,
    val standbyMode: StandbyMode = StandbyMode.default,
)
