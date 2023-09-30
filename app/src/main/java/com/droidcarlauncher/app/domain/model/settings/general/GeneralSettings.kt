/*
 * ScreenSettings.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 16/9/23 11:53
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.general

import com.droidcarlauncher.app.domain.model.settings.clockformat.ClockFormat
import com.droidcarlauncher.app.domain.model.settings.general.orientation.ScreenOrientation
import com.droidcarlauncher.app.domain.model.settings.general.steeringwheelposition.SteeringWheelPosition

data class GeneralSettings(
    val orientation: ScreenOrientation = ScreenOrientation.default,
    val steeringWheelPosition: SteeringWheelPosition = SteeringWheelPosition.default,
    val clockFormat: ClockFormat = ClockFormat.default,
)
