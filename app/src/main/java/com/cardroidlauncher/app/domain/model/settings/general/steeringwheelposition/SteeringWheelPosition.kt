/*
 * SteeringWheelPosition.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 14:36
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.general.steeringwheelposition

import androidx.annotation.StringRes
import com.cardroidlauncher.app.R

sealed class SteeringWheelPosition(
    val value: Int,
    @StringRes val label: Int,
) {

    object Left : SteeringWheelPosition(
        value = LEFT_VALUE,
        label = R.string.left,
    )

    object Right : SteeringWheelPosition(
        value = RIGHT_VALUE,
        label = R.string.right,
    )

    companion object {

        val default = Left

        val values
            get() = SteeringWheelPosition::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val LEFT_VALUE = 0
        private const val RIGHT_VALUE = 1

        fun fromValue(value: Int?): SteeringWheelPosition {
            return when (value) {
                LEFT_VALUE -> Left
                RIGHT_VALUE -> Right
                else -> default
            }
        }
    }
}
