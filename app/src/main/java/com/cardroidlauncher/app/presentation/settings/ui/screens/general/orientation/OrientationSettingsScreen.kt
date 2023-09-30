/*
 * ClockFormatSettingsScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 12:52
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.general.orientation

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
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.settings.general.orientation.ScreenOrientation
import com.cardroidlauncher.app.presentation.main.navigation.NavigationComponent
import com.cardroidlauncher.app.presentation.main.ui.components.listtile.ListTileRadio
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrientationSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: OrientationSettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent,
) {
    val selectedOrientation by viewModel.selectedOrientation.collectAsState()
    val goBack by viewModel.goBack.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.screen_orientation,
                onBackPressed = viewModel::onBackPressed,
            )
        },
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues).fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(ScreenOrientation.values) { orientation ->
                    ListTileRadio(
                        text = stringResource(orientation.label),
                        selected = selectedOrientation == orientation,
                        onClick = { viewModel.setOrientation(orientation) },
                    )
                }
            }
        }
    }
}
