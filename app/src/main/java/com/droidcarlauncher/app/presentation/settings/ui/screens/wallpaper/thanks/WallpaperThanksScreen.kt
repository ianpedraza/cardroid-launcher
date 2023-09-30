/*
 * WallpaperThanksScreen.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 28/9/23 13:12
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens.wallpaper.thanks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.presentation.main.navigation.NavigationComponent
import com.droidcarlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.aspectRatio1to1
import com.droidcarlauncher.app.presentation.main.utils.Utils.launchUrl
import com.droidcarlauncher.app.presentation.settings.ui.screens.wallpaper.components.WallpaperItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperThanksScreen(
    modifier: Modifier = Modifier,
    viewModel: WallpaperThanksViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent
) {
    val goBack by viewModel.goBack.collectAsState()
    val wallpapers by viewModel.wallpapers.collectAsState()
    val wallpaperDetailsUrl by viewModel.wallpaperDetailsUrl.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    if (wallpaperDetailsUrl != null) {
        LocalContext.current.launchUrl(wallpaperDetailsUrl!!)
        viewModel.onLaunchedWallpaperDetails()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.wallpaper_thanks,
                onBackPressed = viewModel::onBackPressed,
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
                columns = GridCells.Adaptive(StandardDimensions.wallpaperItemSize),
            ) {
                items(wallpapers) { item ->
                    val authorString = stringResource(item.author!!)

                    Column(
                        modifier = Modifier
                            .aspectRatio(aspectRatio1to1)
                            .clickable {
                                viewModel.onLaunchWallpaperDetails(authorString)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        WallpaperItem(
                            modifier = Modifier.fillMaxWidth(),
                            wallpaper = item,
                            isSelected = false,
                            onSelected = {
                                viewModel.onLaunchWallpaperDetails(authorString)
                            }
                        )
                        Text(stringResource(item.title!!))
                        Text(stringResource(item.author))
                    }
                }
            }
        }
    }
}
