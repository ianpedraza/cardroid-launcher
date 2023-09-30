/*
 * DrawableAppIcon.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 14:25
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.aspectRatio1to1
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.iconElevation
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.iconSizeMedium

@Composable
fun DrawableAppIcon(
    modifier: Modifier = Modifier,
    drawable: Drawable?,
    contentDescription: String,
    shape: Shape = CircleShape,
    aspectRatio: Float = aspectRatio1to1,
    elevation: Dp = iconElevation,
    size: Dp = iconSizeMedium,
    onClick: () -> Unit,
    paddingValues: PaddingValues = PaddingValues()
) {
    Box(
        modifier = Modifier
            .requiredSize(size)
            .padding(paddingValues)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = elevation,
                    spotColor = Color.Black,
                    shape = CircleShape,
                )
                .background(Color.White)
                .clickable(onClick = onClick)
                .then(modifier),
        ) {
            Image(
                modifier = Modifier.fillMaxSize().aspectRatio(aspectRatio).clip(shape),
                painter = rememberDrawablePainter(drawable),
                contentDescription = contentDescription,
            )
        }
    }
}
