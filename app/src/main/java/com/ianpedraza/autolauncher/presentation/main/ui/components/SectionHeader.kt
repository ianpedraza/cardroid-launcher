/*
 * SectionHeader.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 15:32
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.smallSize

@Composable
fun SectionHeader(text: String) {
    Text(
        modifier = Modifier.padding(horizontal = smallSize),
        text = text,
        style = MaterialTheme.typography.headlineMedium
    )
}
