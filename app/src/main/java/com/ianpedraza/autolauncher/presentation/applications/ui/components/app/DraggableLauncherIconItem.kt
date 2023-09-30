/*
 * DraggableLauncherIconItem.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 14/9/23 15:25
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui.components.app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.presentation.applications.ui.LauncherEvent
import com.ianpedraza.autolauncher.presentation.applications.ui.components.drag.DragAndDropItem
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.appIconSize
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.aspectRatio1to1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableLauncherIconItem(
    modifier: Modifier = Modifier,
    app: AppModel,
    pagerState: PagerState,
    onEvent: (LauncherEvent) -> Unit,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .requiredSize(appIconSize)
            .aspectRatio(aspectRatio1to1)
            .then(modifier),
    ) {
        DragAndDropItem(
            modifier = Modifier.fillMaxSize(),
            data = app,
            context = context,
            pagerState = pagerState,
            onItemMoved = { origin, target ->
                onEvent(LauncherEvent.OnResetStandby)
                onEvent(LauncherEvent.OnAppsDragged(origin, target))
            }
        ) { isDragging ->
            LauncherIconItem(
                modifier = Modifier.fillMaxSize(),
                app = app,
                isDragging = isDragging,
                onClick = { onEvent(LauncherEvent.OnLaunchApp(app)) },
            )
        }
    }
}
