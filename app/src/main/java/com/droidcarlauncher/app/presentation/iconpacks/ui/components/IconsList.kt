/*
 * IconsList.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 17:56
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.iconpacks.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import com.droidcarlauncher.app.presentation.main.ui.components.DrawableAppIcon
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.appIconSizeSmall
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.smallSize

@Composable
fun IconsList(
    modifier: Modifier = Modifier,
    icons: List<CustomIcon>,
    onClick: (CustomIcon) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.then(Modifier.padding(top = smallSize)),
        columns = GridCells.Adaptive(appIconSizeSmall),
    ) {
        items(icons) { customIcon ->
            DrawableAppIcon(
                size = appIconSizeSmall,
                drawable = customIcon.drawable,
                contentDescription = stringResource(R.string.icon),
                onClick = { onClick(customIcon) },
                paddingValues = PaddingValues(StandardDimensions.extraSmallSize)
            )
        }
    }
}
