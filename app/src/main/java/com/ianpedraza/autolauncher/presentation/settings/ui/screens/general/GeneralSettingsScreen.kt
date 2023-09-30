/*
 * GeneralSettingsScreen.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 16/9/23 12:34
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.settings.ui.screens.general

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.domain.model.settings.general.GeneralSettingsOptions
import com.ianpedraza.autolauncher.presentation.main.navigation.NavigationComponent
import com.ianpedraza.autolauncher.presentation.main.ui.components.listtile.ListTileForward
import com.ianpedraza.autolauncher.presentation.main.ui.components.topbar.AppTopBarBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: GeneralSettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent,
) {
    val goBack by viewModel.goBack.collectAsState()
    val launchOrientationSettings by viewModel.launchOrientationSettings.collectAsState()
    val launchSteeringWheelPositionSettings by viewModel.launchSteeringWheelPositionSettings.collectAsState()
    val launchClockFormatSettings by viewModel.launchClockFormatSettings.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    if (launchOrientationSettings) {
        navigationComponent.goToOrientationSettings()
        viewModel.onLaunchedOrientationSettings()
    }

    if (launchSteeringWheelPositionSettings) {
        navigationComponent.goToSteeringWheelPositionSettings()
        viewModel.onLaunchedSteeringWheelPositionSettings()
    }

    if (launchClockFormatSettings) {
        navigationComponent.goToClockFormatSettings()
        viewModel.onLaunchedSClockFormatSettings()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.general,
                onBackPressed = viewModel::onBackPressed,
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
        ) {
            items(GeneralSettingsOptions.values) { option ->
                ListTileForward(
                    text = stringResource(option.label),
                    onClick = { viewModel.onSettingOptionClicked(option) },
                )
            }
        }
    }
}
