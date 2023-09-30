/*
 * ListTileCheckbox.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 18/9/23 19:50
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components.listtile

import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListTileCheckbox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    isChecked: Boolean
) {
    ListTile(
        modifier = modifier,
        leading = {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { onClick.invoke() }
            )
        },
        content = content,
        onClick = onClick
    )
}
