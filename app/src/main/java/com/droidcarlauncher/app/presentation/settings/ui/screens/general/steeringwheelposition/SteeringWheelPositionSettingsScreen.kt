/*
 * SteeringWheelPositionSettingsScreen.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 16/9/23 12:52
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens.general.steeringwheelposition

import androidx.compose.foundation.layout.Box
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
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.settings.general.steeringwheelposition.SteeringWheelPosition
import com.droidcarlauncher.app.presentation.main.navigation.NavigationComponent
import com.droidcarlauncher.app.presentation.main.ui.components.listtile.ListTileRadio
import com.droidcarlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SteeringWheelPositionSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SteeringWheelPositionSettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent,
) {
    val selectedPosition by viewModel.selectedPosition.collectAsState()
    val goBack by viewModel.goBack.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.steering_wheel_position,
                onBackPressed = viewModel::onBackPressed,
            )
        },
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues).fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(SteeringWheelPosition.values) { position ->
                    ListTileRadio(
                        text = stringResource(position.label),
                        selected = selectedPosition == position,
                        onClick = { viewModel.setPosition(position) },
                    )
                }
            }
        }
    }
}
