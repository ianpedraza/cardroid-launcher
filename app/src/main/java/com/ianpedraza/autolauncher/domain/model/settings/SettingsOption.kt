/*
 * SettingsOption.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 25/9/23 14:14
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.model.settings

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.ModeStandby
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.ui.graphics.vector.ImageVector
import com.ianpedraza.autolauncher.R

sealed class SettingsOption(
    val value: Int,
    @StringRes val title: Int,
    val imageVector: ImageVector,
) {

    object General : SettingsOption(
        value = GENERAL_VALUE,
        title = R.string.general,
        imageVector = Icons.Outlined.Settings,
    )

    object Appearance : SettingsOption(
        value = APPEARANCE_VALUE,
        title = R.string.appearance,
        imageVector = Icons.Outlined.DarkMode,
    )

    object Applications : SettingsOption(
        value = APPLICATIONS_VALUE,
        title = R.string.applications,
        imageVector = Icons.Outlined.Apps
    )

    object StandbyMode : SettingsOption(
        value = STANDBY_VALUE,
        title = R.string.standby_mode,
        imageVector = Icons.Outlined.ModeStandby,
    )

    object Wallpaper : SettingsOption(
        value = WALLPAPER_VALUE,
        title = R.string.wallpaper,
        imageVector = Icons.Outlined.Wallpaper,
    )

    companion object {
        val values
            get() = SettingsOption::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val GENERAL_VALUE = 0
        private const val APPEARANCE_VALUE = 1
        private const val APPLICATIONS_VALUE = 2
        private const val STANDBY_VALUE = 3
        private const val WALLPAPER_VALUE = 4
    }
}
