/*
 * IconPackItem.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 15:31
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.droidcarlauncher.app.domain.model.iconpacks.IconPackModel
import com.droidcarlauncher.app.presentation.main.ui.components.listtile.ListTileDrawable
import com.droidcarlauncher.app.presentation.main.utils.Utils.getApplicationIcon

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
