/*
 * fullLineItem.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 14:05
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.mediumSize

fun LazyGridScope.fullLineItem(
    content: @Composable LazyGridItemScope.() -> Unit,
) {
    item(span = { GridItemSpan(maxLineSpan) }, content = content)
}

fun LazyGridScope.fullLineColumn(
    content: @Composable ColumnScope.() -> Unit
) {
    fullLineItem {
        Column(content = content)
    }
}


fun LazyGridScope.fullLineVerticalSpacer(
    size: Dp = mediumSize
) {
    fullLineItem {
        VerticalSpacer(size)
    }
}

