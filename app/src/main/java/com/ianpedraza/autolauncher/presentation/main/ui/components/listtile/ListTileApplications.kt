/*
 * ListTileApplications.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 28/9/23 12:46
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components.listtile

import android.graphics.drawable.Drawable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.presentation.main.ui.components.DrawableAppIcon
import com.ianpedraza.autolauncher.presentation.main.ui.components.HorizontalSpacer
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.iconElevation
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.iconSizeMedium
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.getApplicationIcon

@Composable
fun ListTileApplications(
    modifier: Modifier = Modifier,
    app: AppModel,
    onClick: (AppModel) -> Unit,
    isChecked: Boolean
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    var icon by remember { mutableStateOf<Drawable?>(null) }

    LaunchedEffect(app.id) {
        icon = context.getApplicationIcon(app.packageName)
    }

    ListTileCheckbox(
        modifier = modifier,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                DrawableAppIcon(
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onClick(app) }
                    ),
                    drawable = icon,
                    contentDescription = app.customLabel ?: app.label,
                    size = iconSizeMedium,
                    elevation = iconElevation,
                    onClick = { onClick(app) }
                )
                HorizontalSpacer()
                Text(
                    text = app.label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        },
        onClick = { onClick(app) },
        isChecked = isChecked
    )
}
