/*
 * Extensions.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 14/9/23 18:42
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.luminance

object Extensions {
    val ColorScheme.isDark: Boolean
        @Composable
        @ReadOnlyComposable
        get() = background.luminance() < 0.5f
}
