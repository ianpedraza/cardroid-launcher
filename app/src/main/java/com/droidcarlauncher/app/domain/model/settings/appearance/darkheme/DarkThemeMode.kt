/*
 * DarkThemeMode.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 22/9/23 11:43
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.appearance.darkheme

import androidx.annotation.StringRes
import com.droidcarlauncher.app.R

sealed class DarkThemeMode(
    val value: Int,
    @StringRes val label: Int,
) {
    object System : DarkThemeMode(
        value = SYSTEM_VALUE,
        label = R.string.system_mode,
    )

    object Dark : DarkThemeMode(
        value = DARK_VALUE,
        label = R.string.dark_mode,
    )

    object Light : DarkThemeMode(
        value = LIGHT_VALUE,
        label = R.string.light_mode,
    )

    companion object {

        val default = System

        val values
            get() = DarkThemeMode::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val SYSTEM_VALUE = 0
        private const val DARK_VALUE = 1
        private const val LIGHT_VALUE = 2

        fun fromValue(value: Int?): DarkThemeMode {
            return when (value) {
                SYSTEM_VALUE -> System
                DARK_VALUE -> Dark
                LIGHT_VALUE -> Light
                else -> default
            }
        }
    }
}
