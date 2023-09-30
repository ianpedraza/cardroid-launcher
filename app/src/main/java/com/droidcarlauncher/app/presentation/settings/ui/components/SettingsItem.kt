/*
 * SettingsItem.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 7/9/23 18:39
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.settings.SettingsOption
import com.droidcarlauncher.app.presentation.main.ui.components.icons.AppIcon
import com.droidcarlauncher.app.presentation.main.ui.components.listtile.ListTileForward

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
