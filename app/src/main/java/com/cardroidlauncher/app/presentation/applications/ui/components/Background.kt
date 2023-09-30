/*
 * Background.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 11/9/23 10:58
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Background(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Box(modifier.then(Modifier.fillMaxSize().background(color)))
}
