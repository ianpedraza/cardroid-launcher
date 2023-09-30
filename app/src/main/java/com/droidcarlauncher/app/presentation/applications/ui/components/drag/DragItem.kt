/*
 * DragItem.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 10/9/23 19:03
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components.drag

import android.content.Context
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.utils.extension.DataTypesExtensions.value
import com.droidcarlauncher.app.presentation.applications.ui.components.drag.utils.LocalCurrentDragState
import com.droidcarlauncher.app.presentation.applications.ui.components.drag.utils.DragAndDropConstants.OVERSCROLL_TIMEOUT
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.mediumSize
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.smallBorder
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragItem(
    modifier: Modifier = Modifier,
    context: Context,
    pagerState: PagerState,
    data: AppModel,
    content: @Composable (isDragging: Boolean) -> Unit,
) {
    val currentState = LocalCurrentDragState.current

    val coroutineScope = rememberCoroutineScope()
    var overScrollJob: Job? by remember { mutableStateOf(null) }
    var draggingJob: Job? by remember { mutableStateOf(null) }

    var currentPosition by remember { mutableStateOf(Offset.Zero) }

    val screenWidth = context.resources.displayMetrics.widthPixels

    key(data.id, data.customIconName, data.customLabel) {
        Box(
            modifier = modifier
                .onGloballyPositioned {
                    currentPosition = it.localToWindow(Offset.Zero)
                }
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            with(currentState) {
                                dragOffset = Offset.Zero
                                dragCancelled = false
                                dragEnded = false
                                startDragPosition = currentPosition + it
                                dataDragged = data
                                draggableComposable = { content(true) }
                                isDragging = true
                            }
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            draggingJob?.cancel()
                            overScrollJob?.cancel()
                            draggingJob = coroutineScope.launch {
                                val nextOffset =
                                    currentState.dragOffset + Offset(dragAmount.x, dragAmount.y)

                                val nextPosition = currentState.startDragPosition + nextOffset

                                if (currentState.draggableSurface?.contains(nextPosition) == true) {
                                    currentState.dragOffset = nextOffset
                                }

                                val currentPage = pagerState.currentPage
                                val dragPositionX = currentState.dragPosition.x

                                if (currentState.isInOverscrollStart(dragPositionX) && pagerState.canScrollBackward) {
                                    overScrollJob = coroutineScope.launch {
                                        delay(OVERSCROLL_TIMEOUT)
                                        pagerState.animateScrollToPage(
                                            page = currentPage - 1,
                                            animationSpec = spring(stiffness = Spring.StiffnessMedium),
                                        )
                                    }
                                } else if (
                                    currentState.isInOverscrollEnd(
                                        dragPositionX,
                                        screenWidth.toFloat(),
                                    ) &&
                                    pagerState.canScrollForward
                                ) {
                                    overScrollJob = coroutineScope.launch {
                                        delay(OVERSCROLL_TIMEOUT)
                                        pagerState.animateScrollToPage(
                                            page = currentPage + 1,
                                            animationSpec = spring(stiffness = Spring.StiffnessMedium),
                                        )
                                    }
                                }
                            }
                        },
                        onDragEnd = {
                            with(currentState) {
                                draggingJob?.cancel()
                                overScrollJob?.cancel()
                                dragEnded = true
                                isDragging = false
                                dragCancelled = false
                                draggableComposable = null
                                dragOffset = Offset.Zero
                                startDragPosition = Offset.Zero
                            }
                        },
                        onDragCancel = {
                            with(currentState) {
                                draggingJob?.cancel()
                                overScrollJob?.cancel()
                                dragCancelled = true
                                isDragging = false
                                dragEnded = false
                                dragOffset = Offset.Zero
                                startDragPosition = Offset.Zero
                                draggableComposable = null
                                dataDragged = null
                            }
                        },
                    )
                },
            contentAlignment = Alignment.Center,
        ) {
            val isCurrentItem = data.id == currentState.dataDragged?.id
            if (currentState.isDragging.value() && isCurrentItem) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(mediumSize),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = smallBorder,
                                color = MaterialTheme.colorScheme.onBackground,
                                shape = MaterialTheme.shapes.medium,
                            ),
                    )
                }
            } else {
                content.invoke(false)
            }
        }
    }
}
