/*
 * LinkError.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 14:24
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components.linkerror

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.droidcarlauncher.app.presentation.main.ui.components.HorizontalSpacer
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.smallSize

@Composable
fun LinkError(
    modifier: Modifier = Modifier,
    message: String,
    retryOptions: RetryOptions? = null,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall,
        )
        retryOptions?.run {
            HorizontalSpacer(smallSize)
            RetryButton(
                buttonText = buttonText,
                onButtonClick = onButtonClick,
            )
        }
    }
}
