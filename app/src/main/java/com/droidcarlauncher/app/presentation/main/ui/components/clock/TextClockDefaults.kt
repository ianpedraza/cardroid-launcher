/*
 * TextClockDefaults.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 7/9/23 21:37
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui.components.clock

import android.graphics.Typeface
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight

object TextClockDefaults {
    const val FORMAT_DATE_TIME_12H = "hh:mm a  EEE, MMM. dd"
    const val FORMAT_DATE_TIME_24H = "HH:mm  EEE, MMM. dd"
    const val FORMAT_DATE = "EEE, MMM. dd"
    const val FORMAT_DATE_SHORT = "EEE. dd"
    const val FORMAT_TIME_12H = "hh:mm a"
    const val FORMAT_TIME_24H = "HH:mm"

    fun android.widget.TextClock.setStyle(
        style: TextStyle,
        textColor: Color,
        face: Typeface,
    ) {
        apply {
            textSize.let { this.textSize = style.fontSize.value }
            setTextColor(textColor.toArgb())
            typeface = face
        }
    }

    fun android.widget.TextClock.setFormat(format: String) {
        apply {
            format24Hour?.let { this.format24Hour = format }
            format12Hour?.let { this.format12Hour = format }
            timeZone?.let { this.timeZone = it }
        }
    }

    fun TextStyle.toTypeFaceState(resolver: FontFamily.Resolver): State<Any> {
        return resolver.resolve(
            fontFamily = fontFamily,
            fontWeight = fontWeight ?: FontWeight.Normal,
            fontStyle = fontStyle ?: FontStyle.Normal,
            fontSynthesis = fontSynthesis ?: FontSynthesis.All,
        )
    }
}
