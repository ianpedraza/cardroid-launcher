/*
 * ListTileRadio.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 28/9/23 12:46
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components.listtile

import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.iconScaleFactor

@Composable
fun ListTileRadio(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    ListTileText(
        modifier = modifier,
        text = text,
        leading = {
            RadioButton(
                modifier = Modifier.scale(iconScaleFactor),
                selected = selected,
                onClick = onClick,
            )
        },
        onClick = onClick,
    )
}

@Composable
fun ListTileRadio(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    selected: Boolean
) {
    ListTile(
        modifier = modifier,
        leading = {
            RadioButton(
                modifier = Modifier.scale(iconScaleFactor),
                selected = selected,
                onClick = onClick,
            )
        },
        content = content,
        onClick = onClick
    )
}
