/*
 * PlayStoreTile.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 24/9/23 11:55
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.presentation.main.ui.components.listtile.ListTileDrawable

@Composable
fun PlayStoreTile(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ListTileDrawable(
        modifier = modifier,
        text = stringResource(R.string.get_more_icon_packs),
        drawable = LocalContext.current.getDrawable(R.drawable.google_play_icon),
        onClick = onClick
    )
}
