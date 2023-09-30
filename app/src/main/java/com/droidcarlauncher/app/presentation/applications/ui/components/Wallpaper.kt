/*
 * Wallpaper.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 14/9/23 19:52
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.settings.wallpaper.Wallpaper
import com.droidcarlauncher.app.presentation.main.ui.theme.Extensions.isDark

@Composable
fun Wallpaper(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
) {
    key(wallpaper, MaterialTheme.colorScheme.isDark) {
        Box(modifier = modifier) {
            val image = if (MaterialTheme.colorScheme.isDark) {
                wallpaper.night
            } else {
                wallpaper.day
            }

            Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background))

            image?.let { resource ->
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = resource,
                    contentDescription = stringResource(R.string.wallpaper),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}