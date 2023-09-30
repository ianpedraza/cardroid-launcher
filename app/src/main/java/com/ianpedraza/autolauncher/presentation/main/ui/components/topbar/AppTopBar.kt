/*
 * AppTopBar.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 7/9/23 18:14
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ianpedraza.autolauncher.presentation.main.ui.components.HorizontalExpanded
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.smallSize
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.topBarSize

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(topBarSize)
                .background(MaterialTheme.colorScheme.surface),
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(smallSize),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HorizontalExpanded(contentAlignment = Alignment.CenterStart) {
                navigationIcon.invoke()
            }

            HorizontalExpanded(weight = 3f, contentAlignment = Alignment.Center) {
                title.invoke()
            }

            HorizontalExpanded(contentAlignment = Alignment.CenterEnd) {
                actions.invoke(this@Row)
            }
        }
    }
}
