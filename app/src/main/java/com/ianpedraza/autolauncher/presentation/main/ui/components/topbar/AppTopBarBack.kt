/*
 * AppTopBarBack.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 7/9/23 21:37
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.presentation.main.ui.components.icons.AppIconButton

@Composable
fun AppTopBarBack(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    onBackPressed: () -> Unit,
    icon: ImageVector = Icons.Outlined.ArrowBack,
    actions: @Composable RowScope.() -> Unit = {},
) {
    AppTopBarBack(
        modifier = modifier,
        title = stringResource(title),
        onBackPressed = onBackPressed,
        icon = icon,
        actions = actions,
    )
}


@Composable
fun AppTopBarBack(
    modifier: Modifier = Modifier,
    title: String,
    onBackPressed: () -> Unit,
    icon: ImageVector = Icons.Outlined.ArrowBack,
    actions: @Composable RowScope.() -> Unit = {},
) {
    AppTopBar(
        modifier = modifier,
        title = {
            Text(
                title,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            AppIconButton(
                imageVector = icon,
                contentDescription = stringResource(R.string.go_back),
                onClick = onBackPressed,
            )
        },
        actions = actions
    )
}
