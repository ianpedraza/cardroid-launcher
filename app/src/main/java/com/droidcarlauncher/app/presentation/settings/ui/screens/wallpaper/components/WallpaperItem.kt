/*
 * WallpaperItem.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 28/9/23 13:11
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens.wallpaper.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.settings.wallpaper.Wallpaper
import com.droidcarlauncher.app.presentation.applications.ui.components.Wallpaper
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.aspectRatio16to9
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.aspectRatio1to1
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.mediumSize
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.smallBorder
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.smallSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperItem(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    isSelected: Boolean,
    onSelected: ((Wallpaper) -> Unit)? = null,
) {
    Box(modifier.then(Modifier.padding(smallSize))) {
        Card(
            modifier = Modifier
                .aspectRatio(aspectRatio16to9)
                .border(
                    width = smallBorder,
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium,
                ),
            onClick = { onSelected?.invoke(wallpaper) },
            shape = MaterialTheme.shapes.medium,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Wallpaper(
                    modifier = Modifier.fillMaxSize(),
                    wallpaper = wallpaper,
                )

                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.1f)),
                    )

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .size(mediumSize)
                            .aspectRatio(aspectRatio1to1),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            tint = Color.White,
                            contentDescription = stringResource(R.string.check_icon),
                        )
                    }
                }
            }
        }
    }
}
