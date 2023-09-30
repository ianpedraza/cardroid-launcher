/*
 * StandbyModeSettingsScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 11:49
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.standby

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.settings.standby.StandbyMode
import com.cardroidlauncher.app.presentation.main.navigation.NavigationComponent
import com.cardroidlauncher.app.presentation.main.ui.components.listtile.ListTileRadio
import com.cardroidlauncher.app.presentation.main.ui.components.listtile.ListTileSwitch
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.smallSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandbyModeSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: StandbyModeSettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent,
) {
    val standbyMode by viewModel.standbyMode.collectAsState()
    val standbyModeEnabled by viewModel.standbyModeEnabled.collectAsState()
    val goBack by viewModel.goBack.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.standby_mode,
                onBackPressed = viewModel::onBackPressed,
            )
        },
    ) { paddingValues ->
        Box(Modifier.fillMaxSize().padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    val enabledLabel =
                        if (standbyModeEnabled) R.string.enabled else R.string.disabled
                    Box(Modifier.padding(smallSize)) {
                        ListTileSwitch(
                            text = stringResource(enabledLabel),
                            selected = standbyModeEnabled,
                            onClick = viewModel::toggleStandbyMode,
                        )
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = standbyModeEnabled,
                        enter = expandIn(
                            expandFrom = Alignment.TopCenter,
                            initialSize = { IntSize(it.width, 0) },
                            animationSpec = spring(
                                stiffness = Spring.StiffnessHigh,
                                visibilityThreshold = IntSize.VisibilityThreshold,
                            ),
                        ),
                        exit = shrinkOut(
                            shrinkTowards = Alignment.TopCenter,
                            targetSize = { IntSize(it.width, 0) },
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMedium,
                                visibilityThreshold = IntSize.VisibilityThreshold,
                            ),
                        ),
                    ) {
                        Column(Modifier.fillMaxSize()) {
                            StandbyMode.values.forEach { mode ->
                                ListTileRadio(
                                    text = stringResource(mode.label),
                                    selected = standbyMode == mode,
                                    onClick = { viewModel.setStandbyMode(mode) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
