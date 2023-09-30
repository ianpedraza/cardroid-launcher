/*
 * DragTargetInfo.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 10/9/23 18:51
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components.drag.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.applications.ui.components.drag.utils.DragAndDropConstants.OVERSCROLL_THRESHOLD

internal class DragState {
    var startDragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dataDragged by mutableStateOf<AppModel?>(null)

    var isDragging: Boolean? by mutableStateOf(false)
    var dragEnded: Boolean? by mutableStateOf(false)
    var dragCancelled: Boolean? by mutableStateOf(false)

    var draggableSurface: Rect? by mutableStateOf(Rect.Zero)

    val dragPosition get() = startDragPosition + dragOffset

    fun isInOverScroll(xPosition: Float, maxWith: Float): Boolean {
        return isInOverscrollStart(xPosition) || isInOverscrollEnd(xPosition, maxWith)
    }

    fun isInOverscrollStart(xPosition: Float): Boolean {
        return xPosition < OVERSCROLL_THRESHOLD + (draggableSurface?.left ?: 0f)
    }

    fun isInOverscrollEnd(xPosition: Float, maxWith: Float): Boolean {
        return (draggableSurface?.right ?: maxWith) - xPosition < OVERSCROLL_THRESHOLD
    }
}
