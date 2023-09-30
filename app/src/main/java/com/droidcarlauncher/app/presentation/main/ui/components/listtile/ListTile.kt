/*
 * ListTile.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 18/9/23 19:46
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components.listtile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.droidcarlauncher.app.presentation.main.ui.components.HorizontalExpanded
import com.droidcarlauncher.app.presentation.main.ui.components.HorizontalSpacer
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.smallSize

@Composable
fun ListTile(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(smallSize),
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        leading?.let {
            it.invoke()
            HorizontalSpacer(size = smallSize)
        }

        content.invoke()

        trailing?.let {
            HorizontalExpanded(contentAlignment = Alignment.CenterEnd) {
                it.invoke()
            }
        }
    }
}
