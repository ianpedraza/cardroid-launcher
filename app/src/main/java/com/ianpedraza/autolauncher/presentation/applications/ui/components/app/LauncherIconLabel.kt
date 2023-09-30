/*
 * LauncherIconLabel.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 19:54
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui.components.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.extraSmallSize

@Composable
fun LauncherIconLabel(modifier: Modifier = Modifier, app: AppModel) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .background(Color.DarkGray)
            .padding(extraSmallSize)
            .then(modifier),
    ) {
        Text(
            text = app.customLabel ?: app.label,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Normal,
            ),
            overflow = TextOverflow.Ellipsis,
        )
    }
}
