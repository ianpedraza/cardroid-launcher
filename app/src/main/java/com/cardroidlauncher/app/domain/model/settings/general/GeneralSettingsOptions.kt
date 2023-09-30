/*
 * ScreenSettingsOptions.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 12:13
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.general

import androidx.annotation.StringRes
import com.cardroidlauncher.app.R

sealed class GeneralSettingsOptions(
    val value: Int,
    @StringRes val label: Int,
) {

    object Orientation : GeneralSettingsOptions(
        value = SCREEN_ORIENTATION_VALUE,
        label = R.string.screen_orientation,
    )

    object SteeringWheelPosition : GeneralSettingsOptions(
        value = STEERING_WHEEL_POSITION,
        label = R.string.steering_wheel_position,
    )

    object ClockFormat : GeneralSettingsOptions(
        value = CLOCK_FORMAT,
        label = R.string.clock_format,
    )

    companion object {

        val values
            get() = GeneralSettingsOptions::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val SCREEN_ORIENTATION_VALUE = 0
        private const val STEERING_WHEEL_POSITION = 1
        private const val CLOCK_FORMAT = 2
    }
}
