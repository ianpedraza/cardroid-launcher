/*
 * ScreenSettings.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 16/9/23 11:53
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.model.settings.general

import com.ianpedraza.autolauncher.domain.model.settings.clockformat.ClockFormat
import com.ianpedraza.autolauncher.domain.model.settings.general.orientation.ScreenOrientation
import com.ianpedraza.autolauncher.domain.model.settings.general.steeringwheelposition.SteeringWheelPosition

data class GeneralSettings(
    val orientation: ScreenOrientation = ScreenOrientation.default,
    val steeringWheelPosition: SteeringWheelPosition = SteeringWheelPosition.default,
    val clockFormat: ClockFormat = ClockFormat.default,
)
