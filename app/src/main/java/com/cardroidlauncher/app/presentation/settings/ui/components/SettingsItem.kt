/*
 * SettingsItem.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 7/9/23 18:39
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.settings.SettingsOption
import com.cardroidlauncher.app.presentation.main.ui.components.icons.AppIcon
import com.cardroidlauncher.app.presentation.main.ui.components.listtile.ListTileForward

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    item: SettingsOption,
    onOptionClicked: (SettingsOption) -> Unit,
) {
    with(item) {
        ListTileForward(
            modifier = modifier,
            text = stringResource(title),
            leading = {
                AppIcon(
                    imageVector = imageVector,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "${stringResource(R.string.icon)} ${stringResource(title)}",
                )
            },
            onClick = { onOptionClicked(item) },
        )
    }
}
