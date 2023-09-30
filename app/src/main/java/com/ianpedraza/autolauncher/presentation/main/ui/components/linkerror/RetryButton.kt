/*
 * RetryButton.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 14:31
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RetryButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    Text(
        modifier = modifier.then(
            Modifier.clickable { onButtonClick() },
        ),
        text = buttonText,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
    )
}
