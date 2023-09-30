/*
 * ListTileForward.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 28/9/23 12:46
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components.listtile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.presentation.main.ui.components.icons.AppIcon

@Composable
fun ListTileForward(
    modifier: Modifier = Modifier,
    text: String,
    leading: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    ListTileText(
        modifier = modifier,
        text = text,
        leading = leading,
        trailing = {
            AppIcon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = stringResource(R.string.icon_forward),
            )
        },
        onClick = onClick,
    )
}
