/*
 * OnDragging.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 17/9/23 09:43
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components.drag

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.applications.ui.components.drag.utils.LocalCurrentDragState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnDragging(
    modifier: Modifier = Modifier,
    content: @Composable (isDragging: Boolean, data: AppModel?) -> Unit,
) {
    val currentState = LocalCurrentDragState.current

    val coroutineScope = rememberCoroutineScope()
    var isDraggingJob: Job? = null

    var isDragging by remember { mutableStateOf(false) }

    LaunchedEffect(currentState.isDragging) {
        isDraggingJob?.cancel()
        isDraggingJob = coroutineScope.launch(Dispatchers.IO) {
            if (isDragging) delay(200L)
            isDragging = currentState.isDragging == true
        }
    }

    Box(modifier = modifier) {
        content(isDragging, currentState.dataDragged)
    }
}
