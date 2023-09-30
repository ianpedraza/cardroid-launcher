/*
 * DragAndDropItem.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 12/9/23 19:28
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components.drag

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cardroidlauncher.app.domain.model.applications.AppModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropItem(
    modifier: Modifier,
    data: AppModel,
    context: Context,
    pagerState: PagerState,
    onItemMoved: ((origin: AppModel, target: AppModel) -> Unit)? = null,
    onDragEnd: ((origin: AppModel, target: AppModel) -> Unit)? = null,
    content: @Composable (isDragging: Boolean) -> Unit,
) {
    AppDropContainer(
        modifier = Modifier.fillMaxSize(),
        data = data,
        context = context,
        onItemMoved = onItemMoved,
        onDragEnd = onDragEnd,
    ) {
        DragItem(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            context = context,
            pagerState = pagerState,
            data = data,
            content = content,
        )
    }
}
