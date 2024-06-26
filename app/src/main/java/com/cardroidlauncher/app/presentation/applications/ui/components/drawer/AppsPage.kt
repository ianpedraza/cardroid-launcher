/*
 * AppsPage.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 19:53
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components.drawer

import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.presentation.applications.ui.LauncherEvent
import com.cardroidlauncher.app.presentation.applications.ui.components.app.DraggableLauncherIconItem
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.aspectRatio1to1
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.extraSmallSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppsPage(
    modifier: Modifier = Modifier,
    config: PageConfig,
    apps: List<AppModel>,
    pagerState: PagerState,
    iconSize: Dp,
    labelStyle: TextStyle,
    onEvent: (LauncherEvent) -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(iconSize),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            items(apps, key = { it.id }) { app ->
                DraggableLauncherIconItem(
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 150,
                                easing = EaseOutCirc,
                            ),
                        )
                        .padding(bottom = extraSmallSize)
                        .align(Alignment.Center),
                    app = app,
                    iconSize = iconSize,
                    labelStyle = labelStyle,
                    pagerState = pagerState,
                    onEvent = onEvent,
                )
            }

            if (apps.size < config.pageSize) {
                val remainingSize = config.pageSize - apps.size
                items(remainingSize) {
                    Box(
                        modifier = Modifier
                            .requiredSize(iconSize)
                            .aspectRatio(aspectRatio1to1)
                    )
                }
            }
        }
    }
}
