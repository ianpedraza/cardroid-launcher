/*
 * StandbyModeTime.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 11:43
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.standby

import androidx.annotation.StringRes
import com.cardroidlauncher.app.R

sealed class StandbyMode(
    val value: Long,
    @StringRes val label: Int,
) {

    object FiveSeconds : StandbyMode(FIVE_SECONDS_VALUE, R.string.five_seconds)
    object TwoMinutes : StandbyMode(TWO_MINUTES_VALUE, R.string.two_minutes)
    object FiveMinutes : StandbyMode(FIVE_MINUTES_VALUE, R.string.five_minutes)
    object TenMinutes : StandbyMode(TEN_MINUTES_VALUE, R.string.ten_minutes)
    object FifteenMinutes : StandbyMode(FIFTEEN_MINUTES_VALUE, R.string.fifteen_minutes)
    object TwentyMinutes : StandbyMode(TWENTY_MINUTES_VALUE, R.string.twenty_minutes)
    object TwentyFiveMinutes : StandbyMode(TWENTY_FIVE_MINUTES_VALUE, R.string.twenty_five_minutes)
    object ThirtyMinutes : StandbyMode(THIRTY_MINUTES_VALUE, R.string.thirty_minutes)

    companion object {

        val default = TenMinutes

        val values
            get() = StandbyMode::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val FIVE_SECONDS_VALUE = 5_000L
        private const val TWO_MINUTES_VALUE = 120_000L
        private const val FIVE_MINUTES_VALUE = 300_000L
        private const val TEN_MINUTES_VALUE = 600_000L
        private const val FIFTEEN_MINUTES_VALUE = 900_000L
        private const val TWENTY_MINUTES_VALUE = 1200_000L
        private const val TWENTY_FIVE_MINUTES_VALUE = 1500_000L
        private const val THIRTY_MINUTES_VALUE = 1800_000L

        fun fromValue(value: Long?): StandbyMode {
            return when (value) {
                FIVE_SECONDS_VALUE -> FiveSeconds
                TWO_MINUTES_VALUE -> TwoMinutes
                FIVE_MINUTES_VALUE -> FiveMinutes
                TEN_MINUTES_VALUE -> TenMinutes
                FIFTEEN_MINUTES_VALUE -> FifteenMinutes
                TWENTY_MINUTES_VALUE -> TwentyMinutes
                TWENTY_FIVE_MINUTES_VALUE -> TwentyFiveMinutes
                THIRTY_MINUTES_VALUE -> ThirtyMinutes
                else -> default
            }
        }
    }
}
