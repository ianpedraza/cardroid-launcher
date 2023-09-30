/*
 * LauncherScreen.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 18:41
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui.screens

import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.ianpedraza.autolauncher.presentation.applications.ui.components.EditAppDialog
import com.ianpedraza.autolauncher.presentation.applications.ui.components.StandbyMode
import com.ianpedraza.autolauncher.presentation.applications.ui.components.Wallpaper
import com.ianpedraza.autolauncher.presentation.main.navigation.NavigationComponent
import com.ianpedraza.autolauncher.presentation.main.utils.Modifiers.anyGesture
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.ComposableLifecycle
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.launch
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.launchAppInfo
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.launchVoiceAssistant
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.uninstallApp

@Composable
fun LauncherScreen(
    modifier: Modifier = Modifier,
    viewModel: LauncherViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent,
) {
    val context = LocalContext.current

    val appsState by viewModel.appsState.collectAsState()
    val wallpaper by viewModel.wallpaper.collectAsState()
    val generalSettings by viewModel.generalSettings.collectAsState()
    val standbyModeEnabled by viewModel.standbyModeEnabled.collectAsState()
    val appForLaunch by viewModel.appForLaunch.collectAsState()
    val appToUninstall by viewModel.appToUninstall.collectAsState()
    val appToEdit by viewModel.appToEdit.collectAsState()
    val editAppError by viewModel.editAppError.collectAsState()
    val appToLaunchInfo by viewModel.appToLaunchInfo.collectAsState()
    val isVoiceSearchAvailable by viewModel.isVoiceSearchAvailable.collectAsState()

    val launchClockSettings by viewModel.launchClockSettings.collectAsState()
    val launchSettings by viewModel.launchSettings.collectAsState()
    val launchVoiceSearch by viewModel.launchVoiceSearch.collectAsState()

    val uninstallLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.onUninstalledApp()
        }

    ComposableLifecycle { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            with(viewModel) {
                resumeStandbyTimer()
                fetchSystemApps()
                checkUninstalledApps()
            }
        }
        if (event == Lifecycle.Event.ON_PAUSE) {
            viewModel.pauseStandbyTimer()
        }
    }

    if (appForLaunch != null) {
        context.launch(appForLaunch!!)
        viewModel.onLaunchedApplication()
    }

    if (launchClockSettings) {
        context.startActivity(Intent(Settings.ACTION_DATE_SETTINGS), null)
        viewModel.onLaunchedClockSetting()
    }

    if (launchSettings) {
        navigationComponent.goToSettings()
        viewModel.onLaunchedSettings()
    }

    if (launchVoiceSearch) {
        context.launchVoiceAssistant()
        viewModel.onLaunchedVoiceSearch()
    }

    if (appToUninstall != null) {
        uninstallLauncher.uninstallApp(appToUninstall!!)
    }

    if (appToLaunchInfo != null) {
        context.launchAppInfo(appToLaunchInfo!!)
        viewModel.onLaunchedAppInfo()
    }

    Box(
        modifier.then(
            Modifier
                .fillMaxSize()
                .anyGesture { viewModel.resetStandbyTimer() },
        ),
    ) {
        Wallpaper(
            modifier = Modifier.fillMaxSize(),
            wallpaper = wallpaper,
        )

        generalSettings?.let { settings ->
            LauncherContent(
                viewModel = viewModel,
                clockFormat = settings.clockFormat,
                screenOrientation = settings.orientation,
                steeringWheelPosition = settings.steeringWheelPosition,
                isVoiceSearchAvailable = isVoiceSearchAvailable,
                appsState = appsState,
            )

            if (!standbyModeEnabled && appToEdit != null) {
                EditAppDialog(
                    app = appToEdit!!,
                    onEvent = viewModel::onEvent,
                    error = editAppError
                )
            }

            StandbyMode(
                state = standbyModeEnabled,
                clockFormat = settings.clockFormat,
                onEvent = viewModel::onEvent,
            )
        }
    }
}
