/*
 * IconsSize.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 4/10/23 10:49
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.appearance.iconssize

import androidx.annotation.StringRes
import com.cardroidlauncher.app.R

sealed class IconsSize(
    val value: Int,
    @StringRes val label: Int
) {

    object Small : IconsSize(
        value = SMALL_VALUE,
        label = R.string.small
    )

    object Medium : IconsSize(
        value = MEDIUM_VALUE,
        label = R.string.medium
    )

    object Large : IconsSize(
        value = LARGE_VALUE,
        label = R.string.large
    )

    companion object {

        val default = Medium

        val values
            get() = IconsSize::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val SMALL_VALUE = 0
        private const val MEDIUM_VALUE = 1
        private const val LARGE_VALUE = 2

        fun fromValue(value: Int?): IconsSize {
            return when (value) {
                SMALL_VALUE -> Small
                MEDIUM_VALUE -> Medium
                LARGE_VALUE -> Large
                else -> default
            }
        }
    }
}
