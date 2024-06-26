/*
 * LauncherIconItem.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 19:53
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components.app

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.presentation.main.ui.components.VerticalSpacer
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.appIconSizeFactor
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.appIconSizeFactorDragged
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.iconElevation
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.iconElevationLarge
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.smallSize

@Composable
fun LauncherIconItem(
    modifier: Modifier = Modifier,
    app: AppModel,
    isDragging: Boolean = false,
    iconSize: Dp,
    labelStyle: TextStyle,
    onClick: () -> Unit,
) {
    val dynamicIconElevation by animateDpAsState(
        targetValue = if (isDragging) iconElevationLarge else iconElevation,
        label = app.label,
    )
    val dynamicIconSize by animateDpAsState(
        targetValue = if (isDragging) iconSize * appIconSizeFactorDragged else iconSize * appIconSizeFactor,
        label = app.label,
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.then(Modifier.fillMaxSize()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LauncherIcon(
                app = app,
                size = dynamicIconSize,
                elevation = dynamicIconElevation,
                onClick = onClick,
            )
            if (isDragging.not()) {
                VerticalSpacer(smallSize)
                LauncherIconLabel(app = app, style = labelStyle)
            }
        }
    }
}
