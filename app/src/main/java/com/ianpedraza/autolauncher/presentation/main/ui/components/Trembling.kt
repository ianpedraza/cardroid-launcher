/*
 * Trembling.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 16/9/23 18:29
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun Trembling(
    modifier: Modifier = Modifier,
    isTrembling: Boolean = true,
    content: @Composable () -> Unit,
) {
    val label = "animation"
    val animation = rememberInfiniteTransition(label = label)

    val angle by animation.animateFloat(
        initialValue = 3f,
        targetValue = -3f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 100,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
        label = label,
    )

    Box(
        modifier = modifier.then(
            Modifier.rotate(if (isTrembling) angle else 0f),
        ),
        content = { content.invoke() },
    )
}
