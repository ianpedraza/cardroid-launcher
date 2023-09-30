/*
 * CurrentDragState.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 12/9/23 19:28
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components.drag.utils

import androidx.compose.runtime.compositionLocalOf

internal val LocalCurrentDragState = compositionLocalOf { DragState() }
