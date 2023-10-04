/*
 * AppearanceSettingsOptions.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 22/9/23 11:44
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.appearance

import androidx.annotation.StringRes
import com.cardroidlauncher.app.R

sealed class AppearanceSettingsOptions(
    val value: Int,
    @StringRes val label: Int,
) {

    object IconPack : AppearanceSettingsOptions(
        value = ICON_PACK_VALUE,
        label = R.string.icon_pack,
    )

    object DarkTheme : AppearanceSettingsOptions(
        value = DARK_THEME_VALUE,
        label = R.string.dark_theme
    )

    object ResetIcons : AppearanceSettingsOptions(
        value = RESET_ICONS_VALUE,
        label = R.string.reset_icons
    )

    object IconSize : AppearanceSettingsOptions(
        value = ICONS_SIZE_VALUE,
        label = R.string.icons_size
    )

    companion object {

        val values
            get() = AppearanceSettingsOptions::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val ICON_PACK_VALUE = 0
        private const val DARK_THEME_VALUE = 1
        private const val ICONS_SIZE_VALUE = 2
        private const val RESET_ICONS_VALUE = 3
    }

}
