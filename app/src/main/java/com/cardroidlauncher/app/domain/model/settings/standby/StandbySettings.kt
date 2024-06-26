/*
 * StandbyModeSettings.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 11:44
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.standby

data class StandbySettings(
    val enabled: Boolean = true,
    val standbyMode: StandbyMode = StandbyMode.default,
)
