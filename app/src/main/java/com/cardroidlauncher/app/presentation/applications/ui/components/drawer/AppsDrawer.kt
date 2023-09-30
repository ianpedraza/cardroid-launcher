/*
 * AppsDrawer.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 19:51
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components.drawer

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.presentation.applications.ui.LauncherEvent
import com.cardroidlauncher.app.presentation.applications.ui.components.AppsActionsBar
import com.cardroidlauncher.app.presentation.applications.ui.components.drag.utils.DraggableSurface
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.appIconSize
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.extraSmallSize
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.pagerIndicatorHeight
import com.cardroidlauncher.app.presentation.main.utils.Utils.withDensity
import kotlin.math.floor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppsDrawer(
    modifier: Modifier = Modifier,
    apps: List<AppModel>,
    onEvent: (LauncherEvent) -> Unit,
) {
    val density = LocalContext.current.resources.displayMetrics.density

    var pageConfig by remember { mutableStateOf<PageConfig?>(null) }
    var drawerSize by remember { mutableStateOf<IntSize?>(null) }

    LaunchedEffect(drawerSize) {
        drawerSize?.let { size ->
            val widthInDp = size.width.withDensity(density)
            val heightInDp = size.height.withDensity(density)
            val columns = floor(widthInDp / appIconSize).toInt()
            val rows =
                floor((heightInDp - pagerIndicatorHeight) / appIconSize).toInt()
            val pageSize = rows * columns
            pageConfig = PageConfig(
                pageSize = pageSize,
                columns = columns,
                rows = rows,
            )
        }
    }

    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .onSizeChanged { drawerSize = it },
        ),
    ) {
        DraggableSurface(
            modifier = Modifier.fillMaxSize(),
        ) {
            pageConfig?.let { config ->
                val pages = apps.chunked(config.pageSize)
                val pagerState = rememberPagerState(pageCount = { pages.size })

                Column(
                    modifier = Modifier.animateContentSize().fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AppsActionsBar(onEvent = onEvent)

                    HorizontalPager(
                        modifier = Modifier.weight(1f),
                        state = pagerState,
                        verticalAlignment = Alignment.Top,
                        beyondBoundsPageCount = pages.size,
                    ) { pageIndex ->
                        AppsPage(
                            modifier = Modifier.fillMaxSize(),
                            apps = pages[pageIndex],
                            config = config,
                            onEvent = onEvent,
                            pagerState = pagerState,
                        )
                    }

                    if (pages.size > 1) {
                        PagerIndicator(
                            modifier = Modifier.padding(extraSmallSize),
                            count = pages.size,
                            currentPage = pagerState.currentPage,
                        )
                    }
                }
            }
        }
    }
}
