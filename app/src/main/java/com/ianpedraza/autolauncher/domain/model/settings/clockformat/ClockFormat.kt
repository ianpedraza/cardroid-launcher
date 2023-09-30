/*
 * ClockFormat.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 16/9/23 15:19
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.model.settings.clockformat

import androidx.annotation.StringRes
import com.ianpedraza.autolauncher.R

sealed class ClockFormat(
    val value: Int,
    @StringRes val label: Int,
) {

    object Time24h : ClockFormat(
        value = TIME_24H,
        label = R.string.time_24h,
    )

    object Time12h : ClockFormat(
        value = TIME_12H,
        label = R.string.time_12h,
    )

    companion object {

        val default = Time24h

        val values
            get() = ClockFormat::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val TIME_24H = 0
        private const val TIME_12H = 1

        fun fromValue(value: Int?): ClockFormat {
            return when (value) {
                TIME_24H -> Time24h
                TIME_12H -> Time12h
                else -> default
            }
        }
    }
}
