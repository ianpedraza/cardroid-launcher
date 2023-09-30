/*
 * DropContainer.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 17/9/23 12:14
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components.drag

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.applications.ui.components.drag.utils.LocalCurrentDragState

@Composable
fun DropContainer(
    modifier: Modifier = Modifier,
    onItemDragged: (AppModel) -> Unit,
    content: @Composable BoxScope.(isCurrentDropTarget: Boolean) -> Unit,
) {
    val currentState = LocalCurrentDragState.current
    val dragPosition = currentState.dragPosition

    var isCurrentDropTarget: Boolean by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                it.boundsInWindow().let { rect ->
                    isCurrentDropTarget = rect.contains(dragPosition)
                }
            },
    ) {
        if (isCurrentDropTarget && currentState.dragEnded == true) {
            currentState.dataDragged?.let(onItemDragged)
        }
        content(isCurrentDropTarget)
    }
}
