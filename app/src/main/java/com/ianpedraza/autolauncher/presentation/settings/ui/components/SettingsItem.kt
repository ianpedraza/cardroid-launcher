/*
 * SettingsItem.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 7/9/23 18:39
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.settings.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.domain.model.settings.SettingsOption
import com.ianpedraza.autolauncher.presentation.main.ui.components.icons.AppIcon
import com.ianpedraza.autolauncher.presentation.main.ui.components.listtile.ListTileForward

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
