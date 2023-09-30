/*
 * LauncherIconLabel.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 19:54
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components.app

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
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.extraSmallSize

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
