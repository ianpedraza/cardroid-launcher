/*
 * CurrentDragState.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 12/9/23 19:28
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui.components.drag.utils

import androidx.compose.runtime.compositionLocalOf

internal val LocalCurrentDragState = compositionLocalOf { DragState() }
