/*
 * IconsList.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 17:56
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.presentation.main.ui.components.DrawableAppIcon
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.appIconSizeSmall
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.smallSize

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
