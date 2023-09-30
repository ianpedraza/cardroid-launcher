/*
 * ListTileSwitch.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 28/9/23 12:46
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components.listtile

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun ListTileSwitch(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    ListTileText(
        modifier = modifier.then(
            Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ),
        text = text,
        textStyle = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        trailing = {
            Switch(
                modifier = Modifier,
                checked = selected,
                onCheckedChange = { onClick.invoke() },
            )
        },
        onClick = onClick,
    )
}
