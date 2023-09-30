/*
 * AppDropContainer.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 11/9/23 14:23
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui.components.drag

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.presentation.applications.ui.components.drag.utils.LocalCurrentDragState
import com.ianpedraza.autolauncher.presentation.applications.ui.components.drag.utils.DragAndDropConstants.DROP_DETECTION_TIMELAPSE
import com.ianpedraza.autolauncher.presentation.applications.ui.components.drag.utils.DragAndDropConstants.DROP_MOVED_TIMEOUT
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppDropContainer(
    modifier: Modifier = Modifier,
    data: AppModel,
    context: Context,
    onItemMoved: ((origin: AppModel, target: AppModel) -> Unit)? = null,
    onDragEnd: ((origin: AppModel, target: AppModel) -> Unit)? = null,
    content: (@Composable BoxScope.() -> Unit)? = null,
) {
    val screenWidth = context.resources.displayMetrics.widthPixels

    val currentState = LocalCurrentDragState.current
    val dragPosition = currentState.startDragPosition
    val dragOffset = currentState.dragOffset
    val currentData = currentState.dataDragged

    val coroutineScope = rememberCoroutineScope()
    var currentDropTargetJob: Job? by remember { mutableStateOf(null) }

    var isCurrentDropTarget: Boolean by remember { mutableStateOf(false) }

    var lastMoved: Long? by remember { mutableStateOf(null) }
    var lastMovedJob: Job? by remember { mutableStateOf(null) }

    LaunchedEffect(lastMoved) {
        if (lastMoved != null) {
            lastMovedJob?.cancel()
            lastMovedJob = coroutineScope.launch {
                delay(DROP_MOVED_TIMEOUT)
                lastMoved = null
            }
        }
    }

    key(data.id) {
        Box(
            modifier = modifier
                .onGloballyPositioned {
                    currentDropTargetJob?.cancel()
                    currentDropTargetJob = coroutineScope.launch {
                        delay(DROP_DETECTION_TIMELAPSE)
                        it.boundsInWindow().let { rect ->
                            isCurrentDropTarget =
                                dragOffset != Offset.Zero &&
                                rect.contains(dragPosition + dragOffset) &&
                                !currentState.isInOverScroll(
                                    currentState.dragPosition.x,
                                    screenWidth.toFloat(),
                                )
                        }
                    }
                },
        ) {
            if (isCurrentDropTarget) {
                if (currentData?.id != data.id) {
                    if (currentState.dragEnded == true) {
                        currentData?.let {
                            lastMovedJob?.cancel()
                            lastMoved = null
                            currentState.dataDragged = null
                            onDragEnd?.invoke(it, data)
                        }
                    } else if (currentState.isDragging == true) {
                        currentData?.let {
                            if (lastMoved != it.id) {
                                lastMoved = it.id
                                onItemMoved?.invoke(it, data)
                            }
                        }
                    }
                }
            } else {
                lastMovedJob?.cancel()
                lastMoved = null
            }
            content?.invoke(this)
        }
    }
}
