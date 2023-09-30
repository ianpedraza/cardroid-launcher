/*
 * WallpaperSettingsScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 11:50
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.settings.wallpaper.Wallpaper
import com.cardroidlauncher.app.presentation.main.navigation.NavigationComponent
import com.cardroidlauncher.app.presentation.main.ui.components.icons.AppIconButton
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.wallpaperItemSize
import com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.components.WallpaperItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: WallpaperSettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent,
) {
    val goBack by viewModel.goBack.collectAsState()
    val selectedWallpaper by viewModel.selectedWallpaper.collectAsState()
    val launchWallpapersThanks by viewModel.launchWallpapersThanks.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    if (launchWallpapersThanks) {
        navigationComponent.goToWallpaperThanks()
        viewModel.onLaunchedWallpapersThanks()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.wallpaper,
                onBackPressed = viewModel::onBackPressed,
                actions = {
                    AppIconButton(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = stringResource(R.string.info),
                        onClick = viewModel::onLaunchWallpapersThanks,
                    )
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Adaptive(wallpaperItemSize),
            ) {
                items(Wallpaper.values) { item ->
                    WallpaperItem(
                        modifier = Modifier.fillMaxSize(),
                        wallpaper = item,
                        isSelected = item == selectedWallpaper,
                        onSelected = viewModel::setWallpaper,
                    )
                }
            }
        }
    }
}
