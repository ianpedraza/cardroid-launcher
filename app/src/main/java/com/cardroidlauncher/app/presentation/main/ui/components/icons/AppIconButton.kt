/*
 * AppIconButton.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 18:12
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui.components.icons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.aspectRatio1to1
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.extraSmallSize
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.iconSizeMedium

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    size: Dp = iconSizeMedium,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit,
) {
    Box(modifier) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .requiredSize(size)
                .aspectRatio(aspectRatio1to1)
                .clickable(onClick = onClick)
                .padding(extraSmallSize),
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = imageVector,
                tint = tint,
                contentDescription = contentDescription,
            )
        }
    }
}
