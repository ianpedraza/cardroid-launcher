/*
 * ListTileDrawable.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 17:00
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components.listtile

import android.graphics.drawable.Drawable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidcarlauncher.app.presentation.main.ui.components.DrawableAppIcon

@Composable
fun ListTileDrawable(
    modifier: Modifier = Modifier,
    text: String,
    drawable: Drawable? = null,
    onClick: () -> Unit
) {
    ListTile(
        modifier = modifier,
        leading = {
            DrawableAppIcon(
                drawable = drawable,
                contentDescription = text,
                onClick = onClick
            )
        },
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        onClick = onClick
    )
}
