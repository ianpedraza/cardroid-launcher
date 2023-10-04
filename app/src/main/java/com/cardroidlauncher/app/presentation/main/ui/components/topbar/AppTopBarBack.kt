/*
 * AppTopBarBack.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 7/9/23 21:37
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui.components.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.utils.Constants.EMPTY_STRING
import com.cardroidlauncher.app.presentation.main.ui.components.icons.AppIconButton

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarSearch(
    modifier: Modifier = Modifier,
    title: String,
    onBackPressed: () -> Unit,
    onValueChange: (String) -> Unit,
    isSearchEnabled: Boolean,
    icon: ImageVector = Icons.Outlined.ArrowBack,
) {
    var query by remember { mutableStateOf(EMPTY_STRING) }
    var isSearching by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    AppTopBar(
        modifier = modifier,
        title = {
            if (isSearching) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    value = query,
                    textStyle = MaterialTheme.typography.titleMedium,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colorScheme.onSurface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        placeholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    ),
                    singleLine = true,
                    maxLines = 1,
                    onValueChange = {
                        query = it
                        onValueChange(it)
                    },
                    placeholder = {
                        Text(
                            stringResource(R.string.search),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                )
            } else {
                Text(
                    title,
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
        navigationIcon = {
            AppIconButton(
                imageVector = icon,
                contentDescription = stringResource(R.string.go_back),
                onClick = {
                    if (isSearching) {
                        isSearching = false
                        query = EMPTY_STRING
                        onValueChange(EMPTY_STRING)
                    } else {
                        onBackPressed()
                    }
                },
            )
        },
        actions = {
            if (isSearching && query.isNotEmpty()) {
                AppIconButton(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = stringResource(R.string.close),
                    onClick = {
                        query = EMPTY_STRING
                        onValueChange(EMPTY_STRING)
                    },
                )
            }

            if (isSearchEnabled && isSearching.not()) {
                AppIconButton(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search),
                    onClick = {
                        isSearching = true
                    },
                )
            }
        }
    )

    DisposableEffect(isSearching) {
        if (isSearching) {
            focusRequester.requestFocus()
        }
        onDispose { }
    }
}
