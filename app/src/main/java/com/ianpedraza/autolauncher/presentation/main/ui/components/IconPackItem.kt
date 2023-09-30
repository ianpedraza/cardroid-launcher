/*
 * IconPackItem.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 15:31
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel
import com.ianpedraza.autolauncher.presentation.main.ui.components.listtile.ListTileDrawable
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.getApplicationIcon

@Composable
fun IconPackItem(
    iconPack: IconPackModel,
    onClick: () -> Unit
) {
    val icon = LocalContext.current.getApplicationIcon(iconPack.packageName)
    ListTileDrawable(
        text = iconPack.label,
        drawable = icon,
        onClick = onClick
    )
}
