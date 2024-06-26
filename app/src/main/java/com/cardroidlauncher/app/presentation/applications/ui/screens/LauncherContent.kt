/*
 * LauncherContent.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 15/9/23 14:21
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.settings.appearance.iconssize.IconsSize
import com.cardroidlauncher.app.domain.model.settings.clockformat.ClockFormat
import com.cardroidlauncher.app.domain.model.settings.general.orientation.ScreenOrientation
import com.cardroidlauncher.app.domain.model.settings.general.steeringwheelposition.SteeringWheelPosition
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import com.cardroidlauncher.app.presentation.applications.ui.components.StatusBar
import com.cardroidlauncher.app.presentation.applications.ui.components.drawer.AppsDrawer
import com.cardroidlauncher.app.presentation.main.ui.components.ProgressIndicator
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.LinkError
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.RetryOptions
import com.cardroidlauncher.app.presentation.main.utils.Utils.ComposableLifecycle
import com.cardroidlauncher.app.presentation.main.utils.Utils.getScreenOrientation

@Composable
fun LauncherContent(
    viewModel: LauncherViewModel,
    clockFormat: ClockFormat,
    isVoiceSearchAvailable: Boolean,
    appsState: DataState<List<AppModel>>,
    screenOrientation: ScreenOrientation,
    iconsSize: IconsSize,
    steeringWheelPosition: SteeringWheelPosition,
) {
    val config = LocalConfiguration.current
    var orientation by remember { mutableStateOf<ScreenOrientation?>(null) }

    ComposableLifecycle { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            val newOrientation = if (screenOrientation == ScreenOrientation.Automatic) {
                getScreenOrientation(config)
            } else {
                screenOrientation
            }

            if (newOrientation != orientation) {
                orientation = newOrientation
            }
        }
    }

    orientation?.let {
        if (orientation == ScreenOrientation.Portrait) {
            PortraitLauncherScreen(
                appsState = appsState,
                viewModel = viewModel,
                clockFormat = clockFormat,
                isVoiceSearchAvailable = isVoiceSearchAvailable,
                steeringWheelPosition = steeringWheelPosition,
                iconsSize = iconsSize,
            )
        } else {
            LandscapeLauncherScreen(
                appsState = appsState,
                viewModel = viewModel,
                clockFormat = clockFormat,
                isVoiceSearchAvailable = isVoiceSearchAvailable,
                steeringWheelPosition = steeringWheelPosition,
                iconsSize = iconsSize,
            )
        }
    }
}

@Composable
private fun PortraitLauncherScreen(
    viewModel: LauncherViewModel,
    clockFormat: ClockFormat,
    appsState: DataState<List<AppModel>>,
    isVoiceSearchAvailable: Boolean,
    steeringWheelPosition: SteeringWheelPosition,
    iconsSize: IconsSize,
) {
    Column {
        when (appsState) {
            is DataState.Error -> {
                LinkError(
                    modifier = Modifier.weight(1f),
                    message = stringResource(R.string.error_getting_system_apps),
                    retryOptions = RetryOptions(
                        buttonText = stringResource(R.string.retry),
                        onButtonClick = viewModel::fetchApps,
                    ),
                )
            }

            DataState.Loading -> {
                ProgressIndicator(Modifier.weight(1f))
            }

            is DataState.Success -> {
                AppsDrawer(
                    modifier = Modifier.weight(1f),
                    apps = appsState.data,
                    iconsSize = iconsSize,
                    onEvent = viewModel::onEvent,
                )
            }
        }

        StatusBar(
            orientation = ScreenOrientation.Portrait,
            clockFormat = clockFormat,
            steeringWheelPosition = steeringWheelPosition,
            isVoiceSearchAvailable = isVoiceSearchAvailable,
            onEvent = viewModel::onEvent,
        )
    }
}

@Composable
private fun LandscapeLauncherScreen(
    viewModel: LauncherViewModel,
    clockFormat: ClockFormat,
    appsState: DataState<List<AppModel>>,
    isVoiceSearchAvailable: Boolean,
    steeringWheelPosition: SteeringWheelPosition,
    iconsSize: IconsSize,
) {
    Row {
        if (steeringWheelPosition == SteeringWheelPosition.Left) {
            StatusBar(
                orientation = ScreenOrientation.Landscape,
                clockFormat = clockFormat,
                steeringWheelPosition = steeringWheelPosition,
                isVoiceSearchAvailable = isVoiceSearchAvailable,
                onEvent = viewModel::onEvent,
            )
        }

        when (appsState) {
            is DataState.Error -> {
                LinkError(
                    modifier = Modifier.weight(1f),
                    message = stringResource(R.string.error_getting_system_apps),
                    retryOptions = RetryOptions(
                        buttonText = stringResource(R.string.retry),
                        onButtonClick = viewModel::fetchApps,
                    ),
                )
            }

            DataState.Loading -> {
                ProgressIndicator(Modifier.weight(1f))
            }

            is DataState.Success -> {
                AppsDrawer(
                    modifier = Modifier.weight(1f),
                    apps = appsState.data,
                    onEvent = viewModel::onEvent,
                    iconsSize = iconsSize
                )
            }
        }

        if (steeringWheelPosition == SteeringWheelPosition.Right) {
            StatusBar(
                orientation = ScreenOrientation.Landscape,
                clockFormat = clockFormat,
                steeringWheelPosition = steeringWheelPosition,
                isVoiceSearchAvailable = isVoiceSearchAvailable,
                onEvent = viewModel::onEvent,
            )
        }
    }
}
