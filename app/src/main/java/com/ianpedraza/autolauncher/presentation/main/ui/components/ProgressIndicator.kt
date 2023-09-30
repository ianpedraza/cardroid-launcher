/*
 * ProgressIndicator.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 14:04
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.mediumSize

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.then(
            Modifier.padding(mediumSize),
        ),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
