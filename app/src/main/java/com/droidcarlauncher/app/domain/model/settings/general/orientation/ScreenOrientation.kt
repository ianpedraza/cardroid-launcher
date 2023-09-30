/*
 * ScreenOrientation.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 16/9/23 12:28
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.general.orientation

import androidx.annotation.StringRes
import com.droidcarlauncher.app.R

sealed class ScreenOrientation(
    val value: Int,
    @StringRes val label: Int,
) {
    object Automatic : ScreenOrientation(
        value = AUTOMATIC_VALUE,
        label = R.string.automatic,
    )

    object Portrait : ScreenOrientation(
        value = PORTRAIT_VALUE,
        label = R.string.portrait,
    )

    object Landscape : ScreenOrientation(
        value = LANDSCAPE_VALUE,
        label = R.string.landscape,
    )

    companion object {

        val default = Automatic

        val values
            get() = ScreenOrientation::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val AUTOMATIC_VALUE = 0
        private const val LANDSCAPE_VALUE = 1
        private const val PORTRAIT_VALUE = 2

        fun fromValue(value: Int?): ScreenOrientation {
            return when (value) {
                AUTOMATIC_VALUE -> Automatic
                PORTRAIT_VALUE -> Portrait
                LANDSCAPE_VALUE -> Landscape
                else -> Automatic
            }
        }
    }
}
