/*
 * PagerIndicator.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 19:52
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.aspectRatio1to1
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.extraSmallSize
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.pagerIndicatorHeight
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.pagerIndicatorItemMargin
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.pagerIndicatorSize

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    currentPage: Int,
) {
    Box(
        modifier = modifier.then(Modifier.height(pagerIndicatorHeight)),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            Modifier
                .clip(CircleShape)
                .background(Color.DarkGray)
                .padding(extraSmallSize),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(count) { iteration ->
                val color = if (currentPage == iteration) Color.White else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(horizontal = pagerIndicatorItemMargin)
                        .size(pagerIndicatorSize)
                        .aspectRatio(aspectRatio1to1)
                        .clip(CircleShape)
                        .background(color),
                )
            }
        }
    }
}
