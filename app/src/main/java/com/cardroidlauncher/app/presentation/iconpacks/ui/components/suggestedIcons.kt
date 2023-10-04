/*
 * suggestedIcons.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 21/9/23 11:18
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.iconpacks.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import com.cardroidlauncher.app.presentation.main.ui.components.DrawableAppIcon
import com.cardroidlauncher.app.presentation.main.ui.components.ProgressIndicator
import com.cardroidlauncher.app.presentation.main.ui.components.SectionHeader
import com.cardroidlauncher.app.presentation.main.ui.components.fullLineItem
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.LinkError
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.RetryOptions
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.textErrorHeight

fun LazyGridScope.suggestedIcons(
    suggestedIconsState: DataState<List<CustomIcon>>,
    onClick: (CustomIcon) -> Unit,
    onRetry: () -> Unit,
    canRetryGetSuggestions: Boolean
) {
    fullLineItem {
        SectionHeader(stringResource(R.string.suggested))
    }

    when (suggestedIconsState) {
        is DataState.Error -> {
            fullLineItem {
                LinkError(
                    modifier = Modifier
                        .fillMaxSize()
                        .heightIn(min = textErrorHeight),
                    message = stringResource(R.string.error_getting_suggestions),
                    if (canRetryGetSuggestions) {
                        RetryOptions(
                            buttonText = stringResource(R.string.retry),
                            onButtonClick = onRetry,
                        )
                    } else {
                        null
                    },
                )
            }
        }

        DataState.Loading -> {
            fullLineItem {
                ProgressIndicator(
                    Modifier
                        .fillMaxSize()
                        .heightIn(min = textErrorHeight))
            }
        }


        is DataState.Success -> {
            val data = suggestedIconsState.data

            if (data.isEmpty()) {
                fullLineItem {
                    LinkError(
                        modifier = Modifier
                            .fillMaxSize()
                            .heightIn(min = textErrorHeight),
                        message = stringResource(R.string.no_suggestions_found),
                        retryOptions =
                        if (canRetryGetSuggestions) {
                            RetryOptions(
                                buttonText = stringResource(R.string.retry),
                                onButtonClick = onRetry,
                            )
                        } else {
                            null
                        },
                    )
                }
            } else {
                items(data) { customIcon ->
                    DrawableAppIcon(
                        size = StandardDimensions.appIconSize,
                        drawable = customIcon.drawable,
                        contentDescription = stringResource(R.string.icon),
                        onClick = { onClick(customIcon) },
                        paddingValues = PaddingValues(StandardDimensions.extraSmallSize)
                    )
                }
            }
        }
    }
}
