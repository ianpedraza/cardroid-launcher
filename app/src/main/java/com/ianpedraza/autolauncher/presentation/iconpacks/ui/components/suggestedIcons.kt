/*
 * suggestedIcons.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 21/9/23 11:18
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import com.ianpedraza.autolauncher.presentation.main.ui.components.DrawableAppIcon
import com.ianpedraza.autolauncher.presentation.main.ui.components.ProgressIndicator
import com.ianpedraza.autolauncher.presentation.main.ui.components.SectionHeader
import com.ianpedraza.autolauncher.presentation.main.ui.components.fullLineItem
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.LinkError
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.RetryOptions
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.textErrorHeight

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
                        size = StandardDimensions.appIconSizeSmall,
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
