/*
 * TextClock.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 28/9/23 12:41
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui.components.clock

import android.graphics.Typeface
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.viewinterop.AndroidView
import com.cardroidlauncher.app.presentation.main.ui.components.clock.TextClockDefaults.setFormat
import com.cardroidlauncher.app.presentation.main.ui.components.clock.TextClockDefaults.setStyle
import com.cardroidlauncher.app.presentation.main.ui.components.clock.TextClockDefaults.toTypeFaceState

@Composable
fun TextClock(
    modifier: Modifier = Modifier,
    format: String = TextClockDefaults.FORMAT_DATE_TIME_24H,
    color: Color = Color.Unspecified,
    style: TextStyle = MaterialTheme.typography.headlineSmall,
) {
    val textColor = color.takeOrElse {
        style.color.takeOrElse { LocalContentColor.current }
    }

    val resolver = LocalFontFamilyResolver.current
    val face: Typeface = remember(resolver, style) {
        style.toTypeFaceState(resolver)
    }.value as Typeface

    AndroidView(
        modifier = modifier,
        factory = { context ->
            android.widget.TextClock(context).apply {
                setFormat(format)
                setStyle(style, textColor, face)
            }
        },
        update = { textClock ->
            textClock.setStyle(style, textColor, face)
        },
    )
}
