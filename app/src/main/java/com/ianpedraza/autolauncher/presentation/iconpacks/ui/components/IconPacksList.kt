/*
 * IconPacksList.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 25/9/23 14:17
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import com.ianpedraza.autolauncher.presentation.main.ui.components.IconPackItem
import com.ianpedraza.autolauncher.presentation.main.ui.components.ProgressIndicator
import com.ianpedraza.autolauncher.presentation.main.ui.components.SectionHeader
import com.ianpedraza.autolauncher.presentation.main.ui.components.VerticalSpacer
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.LinkError
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.RetryOptions
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.smallSize
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.textErrorHeight

@Composable
fun IconPacksList(
    iconPacksState: DataState<List<IconPackModel>>,
    onRetry: () -> Unit,
    onClick: (IconPackModel) -> Unit,
    showHeader: Boolean = true
): Unit = when (iconPacksState) {
    is DataState.Error -> {
        LinkError(
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = textErrorHeight),
            message = stringResource(R.string.error_getting_icon_packs),
            retryOptions = RetryOptions(
                buttonText = stringResource(R.string.retry),
                onButtonClick = onRetry,
            ),
        )
    }

    DataState.Loading -> {
        ProgressIndicator(
            Modifier
                .fillMaxSize()
                .heightIn(min = textErrorHeight)
        )
    }

    is DataState.Success -> {
        val iconPacks = iconPacksState.data
        Column {
            if (showHeader) {
                SectionHeader(stringResource(R.string.icon_packs))
                VerticalSpacer(smallSize)
            }
            iconPacks.forEach { iconPack ->
                IconPackItem(
                    iconPack = iconPack,
                    onClick = { onClick(iconPack) }
                )
            }
        }
    }
}

