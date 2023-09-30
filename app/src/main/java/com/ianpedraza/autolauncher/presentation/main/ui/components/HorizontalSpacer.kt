/*
 * HorizontalSpacer.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 14:24
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.mediumSize

@Composable
fun HorizontalSpacer(size: Dp = mediumSize) {
    Spacer(Modifier.width(size))
}
