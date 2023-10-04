/*
 * IconsList.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 17:56
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.iconpacks.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.presentation.main.ui.components.DrawableAppIcon
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.appIconSize
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.smallSize

@Composable
fun IconsList(
    modifier: Modifier = Modifier,
    icons: List<CustomIcon>,
    onClick: (CustomIcon) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.then(Modifier.padding(top = smallSize)),
        columns = GridCells.Adaptive(appIconSize),
    ) {
        items(icons) { customIcon ->
            DrawableAppIcon(
                size = appIconSize,
                drawable = customIcon.drawable,
                contentDescription = stringResource(R.string.icon),
                onClick = { onClick(customIcon) },
                paddingValues = PaddingValues(StandardDimensions.extraSmallSize)
            )
        }
    }
}
