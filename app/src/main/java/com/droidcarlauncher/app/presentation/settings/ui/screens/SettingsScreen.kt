/*
 * SettingsScreen.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 18:41
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.settings.SettingsOption
import com.droidcarlauncher.app.presentation.main.navigation.NavigationComponent
import com.droidcarlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.droidcarlauncher.app.presentation.settings.ui.components.SettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent,
) {
    val goBack by viewModel.goBack.collectAsState()
    val launchGeneralSettings by viewModel.launchGeneralSettings.collectAsState()
    val launchDarkModeSettings by viewModel.launchDarkModeSettings.collectAsState()
    val launchStandbyModeSettings by viewModel.launchStandbyModeSettings.collectAsState()
    val launchWallpaperSettings by viewModel.launchWallpaperSettings.collectAsState()
    val launchApplicationsSettings by viewModel.launchApplicationsSettings.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    if (launchGeneralSettings) {
        navigationComponent.goToGeneralSettings()
        viewModel.onLaunchedGeneralSettings()
    }

    if (launchDarkModeSettings) {
        navigationComponent.goToAppearanceSettings()
        viewModel.onLaunchedDarkModeSettings()
    }

    if (launchStandbyModeSettings) {
        navigationComponent.goToStandbyModeSettings()
        viewModel.onLaunchedStandbyModeSettings()
    }

    if (launchWallpaperSettings) {
        navigationComponent.goToWallpaperSettings()
        viewModel.onLaunchedWallpaperSettings()
    }

    if (launchApplicationsSettings) {
        navigationComponent.goToApplicationsSettings()
        viewModel.onLaunchedApplicationsSettings()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.settings,
                onBackPressed = viewModel::onBackPressed,
                icon = Icons.Outlined.Close,
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            items(SettingsOption.values) {
                SettingsItem(
                    item = it,
                    onOptionClicked = viewModel::onSettingOptionClicked,
                )
            }
        }
    }
}
