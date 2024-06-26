/*
 * LauncherIcon.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 19:54
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components.app

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.presentation.main.ui.components.DrawableAppIcon
import com.cardroidlauncher.app.presentation.main.utils.Utils.getCustomIcon
import com.cardroidlauncher.app.presentation.main.utils.Utils.getStockIcon

@Composable
fun LauncherIcon(
    modifier: Modifier = Modifier,
    app: AppModel,
    size: Dp,
    elevation: Dp,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    var icon by remember { mutableStateOf<Drawable?>(null) }

    LaunchedEffect(app.customIconName) {
        icon = if (app.customIconName != null) {
            context.getCustomIcon(app.customIconName)
        } else {
            context.getStockIcon(app)
        }
    }

    DrawableAppIcon(
        modifier = modifier.then(Modifier.fillMaxSize()),
        drawable = icon,
        contentDescription = app.customLabel ?: app.label,
        size = size,
        elevation = elevation,
        onClick = onClick
    )
}
