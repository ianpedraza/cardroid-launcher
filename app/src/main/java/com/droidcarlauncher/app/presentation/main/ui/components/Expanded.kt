/*
 * Expanded.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 7/9/23 21:43
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RowScope.HorizontalExpanded(
    modifier: Modifier = Modifier,
    weight: Float = 1f,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier.then(
            Modifier.weight(weight),
        ),
        contentAlignment = contentAlignment,
        content = content,
    )
}

@Composable
fun ColumnScope.VerticalExpanded(
    modifier: Modifier = Modifier,
    weight: Float = 1f,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier.then(
            Modifier.weight(weight),
        ),
        contentAlignment = contentAlignment,
        content = content,
    )
}
