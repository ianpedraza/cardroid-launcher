package com.cardroidlauncher.app.presentation.main.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.cardroidlauncher.app.domain.model.settings.appearance.darkheme.DarkThemeMode

private val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    secondary = BlueGrey80,
    tertiary = Purple80,
    background = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = BlueGrey40,
    tertiary = Purple40,
    background = Color.White.apply { },
)

@Composable
private fun getColorScheme(
    darkThemeMode: DarkThemeMode,
    dynamic: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
): ColorScheme {
    return if (dynamic) {
        val context = LocalContext.current
        when (darkThemeMode) {
            DarkThemeMode.Dark -> dynamicDarkColorScheme(context)
            DarkThemeMode.Light -> dynamicLightColorScheme(context)
            DarkThemeMode.System -> if (isSystemInDarkTheme()) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }
    } else {
        when (darkThemeMode) {
            DarkThemeMode.Dark -> DarkColorScheme
            DarkThemeMode.Light -> LightColorScheme
            DarkThemeMode.System -> if (isSystemInDarkTheme()) {
                DarkColorScheme
            } else {
                LightColorScheme
            }
        }
    }
}

@Composable
fun CardroidLauncherTheme(
    darkThemeMode: DarkThemeMode,
    content: @Composable () -> Unit,
) {
    val colorScheme = getColorScheme(darkThemeMode)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
