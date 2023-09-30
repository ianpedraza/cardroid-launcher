/*
 * DraggableSurface.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 12/9/23 19:28
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components.drag.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.droidcarlauncher.app.domain.utils.extension.DataTypesExtensions.value

@Composable
fun DraggableSurface(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val state = remember { DragState() }

    CompositionLocalProvider(LocalCurrentDragState provides state) {

        var targetSize by remember { mutableStateOf(IntSize.Zero) }

        Box(
            modifier = modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    it
                        .boundsInWindow()
                        .let { rect ->
                            state.draggableSurface = rect
                        }
                },
        ) {
            content()
            if (state.isDragging.value()) {
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            state.draggableSurface
                                ?.let { rect ->
                                    val x = if (rect.left > 0f) rect.left else 0f
                                    val y = if (rect.top > 0f) rect.top else 0f
                                    Offset(x, y)
                                }
                                ?.let { surfaceOffset ->
                                    val offset = state.dragPosition - surfaceOffset
                                    translationX = offset.x.minus(targetSize.width / 2)
                                    translationY = offset.y.minus(targetSize.height / 2)
                                }
                        }
                        .onGloballyPositioned { targetSize = it.size },
                ) {
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}
