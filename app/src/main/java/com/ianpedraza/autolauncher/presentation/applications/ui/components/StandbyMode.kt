/*
 * StandbyMode.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 12:44
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ianpedraza.autolauncher.domain.model.settings.clockformat.ClockFormat
import com.ianpedraza.autolauncher.presentation.applications.ui.LauncherEvent
import com.ianpedraza.autolauncher.presentation.main.ui.components.clock.TextClock
import com.ianpedraza.autolauncher.presentation.main.ui.components.clock.TextClockDefaults
import com.ianpedraza.autolauncher.presentation.main.utils.Modifiers.anyGesture

@Composable
fun StandbyMode(
    modifier: Modifier = Modifier,
    state: Boolean,
    clockFormat: ClockFormat,
    onEvent: (LauncherEvent) -> Unit,
) {
    AnimatedVisibility(
        visible = state,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier.then(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .anyGesture {
                        onEvent(LauncherEvent.OnResetStandby)
                    },
            ),
            contentAlignment = Alignment.Center,
        ) {
            TextClock(
                style = MaterialTheme.typography.displayLarge,
                format = if (clockFormat == ClockFormat.Time24h) {
                    TextClockDefaults.FORMAT_DATE_TIME_24H
                } else {
                    TextClockDefaults.FORMAT_DATE_TIME_12H
                },
            )
        }
    }
}
