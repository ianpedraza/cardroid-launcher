/*
 * ListTileText.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 18/9/23 19:45
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components.listtile

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun ListTileText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    ListTile(
        modifier = modifier,
        leading = leading,
        content = {
            Text(
                text = text,
                style = textStyle,
            )
        },
        trailing = trailing,
        onClick = onClick
    )
}
