/*
 * AppearanceSettingsScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 22/9/23 12:00
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.appearance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.settings.appearance.AppearanceSettingsOptions
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import com.cardroidlauncher.app.presentation.main.navigation.NavigationComponent
import com.cardroidlauncher.app.presentation.main.ui.components.listtile.ListTileForward
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.largeSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppearanceSettingsScreen(
    modifier: Modifier = Modifier,
    navigationComponent: NavigationComponent,
    viewModel: AppearanceSettingsViewModel = hiltViewModel()
) {
    val goBack by viewModel.goBack.collectAsState()

    val launchDarkThemeSettings by viewModel.launchDarkThemeSettings.collectAsState()
    val launchIconPackSettings by viewModel.launchIconPackSettings.collectAsState()
    val showResetIcons by viewModel.showResetIconsDialog.collectAsState()
    val resetIconsState by viewModel.resetIconsState.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    if (launchDarkThemeSettings) {
        navigationComponent.goToDarkThemeSettings()
        viewModel.onLaunchedDarkThemeSettings()
    }

    if (launchIconPackSettings) {
        navigationComponent.goToIconPackSettings()
        viewModel.onLaunchedIconPackSettings()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.appearance,
                onBackPressed = viewModel::onBackPressed,
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(AppearanceSettingsOptions.values) { option ->
                    ListTileForward(
                        text = stringResource(option.label),
                        onClick = { viewModel.onSettingsOptionClicked(option) },
                    )
                }
            }

            if (showResetIcons) {
                AlertDialog(
                    onDismissRequest = viewModel::onResetIconsDialogDismiss,
                    confirmButton = {
                        Button(onClick = viewModel::onConfirmResetIcons) {
                            Text(stringResource(R.string.ok))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = viewModel::onResetIconsDialogDismiss) {
                            Text(stringResource(R.string.cancel))
                        }
                    },
                    title = { Text(stringResource(R.string.reset_icons)) },
                    text = { Text(stringResource(R.string.reset_icons_instructions)) }
                )
            }

            resetIconsState?.let { state ->
                when (state) {
                    is DataState.Error -> {
                        AlertDialog(
                            onDismissRequest = viewModel::onResetIconsStateDismiss,
                            confirmButton = {
                                Button(onClick = viewModel::onResetIconsStateDismiss) {
                                    Text(stringResource(R.string.ok))
                                }
                            },
                            text = { Text(stringResource(R.string.e_reset_icons)) }
                        )
                    }

                    DataState.Loading -> {
                        Dialog(
                            onDismissRequest = {},
                            properties = DialogProperties(
                                dismissOnBackPress = false,
                                dismissOnClickOutside = false
                            )
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(largeSize)
                                    .background(
                                        color = MaterialTheme.colorScheme.surface,
                                        shape = MaterialTheme.shapes.medium
                                    )
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    is DataState.Success -> {
                        AlertDialog(
                            onDismissRequest = viewModel::onResetIconsStateDismiss,
                            confirmButton = {
                                Button(onClick = viewModel::onResetIconsStateDismiss) {
                                    Text(stringResource(R.string.ok))
                                }
                            },
                            text = { Text(stringResource(R.string.icons_reset)) }
                        )
                    }
                }
            }
        }

    }
}
