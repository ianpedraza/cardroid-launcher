/*
 * StatusBar.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 18:38
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dehaze
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.settings.clockformat.ClockFormat
import com.cardroidlauncher.app.domain.model.settings.general.orientation.ScreenOrientation
import com.cardroidlauncher.app.domain.model.settings.general.steeringwheelposition.SteeringWheelPosition
import com.cardroidlauncher.app.presentation.applications.ui.LauncherEvent
import com.cardroidlauncher.app.presentation.main.ui.components.HorizontalExpanded
import com.cardroidlauncher.app.presentation.main.ui.components.VerticalExpanded
import com.cardroidlauncher.app.presentation.main.ui.components.clock.TextClock
import com.cardroidlauncher.app.presentation.main.ui.components.clock.TextClockDefaults
import com.cardroidlauncher.app.presentation.main.ui.components.icons.AppIconButton
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.iconSizeMedium
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.smallSize
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.statusBarSizeLandscape
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.statusBarSizePortrait

@Composable
fun StatusBar(
    modifier: Modifier = Modifier,
    orientation: ScreenOrientation,
    clockFormat: ClockFormat,
    isVoiceSearchAvailable: Boolean,
    steeringWheelPosition: SteeringWheelPosition,
    onEvent: (LauncherEvent) -> Unit,
) {
    if (orientation == ScreenOrientation.Portrait) {
        PortraitStatusBar(
            modifier = modifier.zIndex(Float.MAX_VALUE),
            clockFormat = clockFormat,
            isVoiceSearchAvailable = isVoiceSearchAvailable,
            steeringWheelPosition = steeringWheelPosition,
            onEvent = onEvent,
        )
    } else {
        LandscapeStatusBar(
            modifier = modifier.zIndex(Float.MAX_VALUE),
            clockFormat = clockFormat,
            isVoiceSearchAvailable = isVoiceSearchAvailable,
            onEvent = onEvent,
        )
    }
}

@Composable
private fun LandscapeStatusBar(
    modifier: Modifier = Modifier,
    clockFormat: ClockFormat,
    isVoiceSearchAvailable: Boolean,
    onEvent: (LauncherEvent) -> Unit,
) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxHeight()
                .width(statusBarSizeLandscape)
        ),
    ) {
        Background()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(smallSize),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ClockWidget(
                onEvent = onEvent,
                clockFormat = clockFormat,
            )

            VerticalExpanded()

            VoiceSearchButton(
                isVoiceSearchAvailable = isVoiceSearchAvailable,
                onEvent = onEvent,
            )

            MenuButton(onEvent = onEvent)
        }
    }
}

@Composable
private fun PortraitStatusBar(
    modifier: Modifier = Modifier,
    clockFormat: ClockFormat,
    isVoiceSearchAvailable: Boolean,
    steeringWheelPosition: SteeringWheelPosition,
    onEvent: (LauncherEvent) -> Unit,
) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(statusBarSizePortrait)
        ),
    ) {
        Background()

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(smallSize),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (steeringWheelPosition == SteeringWheelPosition.Left) {
                MenuButton(onEvent = onEvent)

                VoiceSearchButton(
                    isVoiceSearchAvailable = isVoiceSearchAvailable,
                    onEvent = onEvent,
                )

                HorizontalExpanded()

                ClockWidget(
                    onEvent = onEvent,
                    clockFormat = clockFormat,
                )
            } else if (steeringWheelPosition == SteeringWheelPosition.Right) {
                ClockWidget(
                    onEvent = onEvent,
                    clockFormat = clockFormat,
                )

                HorizontalExpanded()

                VoiceSearchButton(
                    isVoiceSearchAvailable = isVoiceSearchAvailable,
                    onEvent = onEvent,
                )

                MenuButton(onEvent = onEvent)
            }
        }
    }
}

@Composable
private fun Background() {
    Background(color = MaterialTheme.colorScheme.surface)
}

@Composable
private fun ClockWidget(
    onEvent: (LauncherEvent) -> Unit,
    clockFormat: ClockFormat,
) {
    Column(
        modifier = Modifier.clickable {
            onEvent(LauncherEvent.OnLaunchClockSettings)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextClock(
            format = if (clockFormat == ClockFormat.Time24h) {
                TextClockDefaults.FORMAT_TIME_24H
            } else {
                TextClockDefaults.FORMAT_TIME_12H
            },
        )
        TextClock(
            format = TextClockDefaults.FORMAT_DATE_SHORT,
        )
    }
}

@Composable
private fun VoiceSearchButton(
    isVoiceSearchAvailable: Boolean,
    onEvent: (LauncherEvent) -> Unit,
) {
    if (isVoiceSearchAvailable) {
        AppIconButton(
            modifier = Modifier.padding(
                horizontal = smallSize,
                vertical = smallSize,
            ),
            size = iconSizeMedium,
            imageVector = Icons.Outlined.Mic,
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = stringResource(R.string.voice_search),
            onClick = { onEvent(LauncherEvent.OnLaunchVoiceSearch) },
        )
    }
}

@Composable
private fun MenuButton(onEvent: (LauncherEvent) -> Unit) {
    AppIconButton(
        modifier = Modifier.padding(
            horizontal = smallSize,
            vertical = smallSize,
        ),
        size = iconSizeMedium,
        imageVector = Icons.Outlined.Dehaze,
        tint = MaterialTheme.colorScheme.onSurface,
        contentDescription = stringResource(R.string.menu_button),
        onClick = { onEvent(LauncherEvent.OnLaunchSettings) },
    )
}
