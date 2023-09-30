package com.cardroidlauncher.app.presentation.main.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
fun CardroidLauncherTheme(
    darkThemeMode: DarkThemeMode,
    content: @Composable () -> Unit,
) {
    val colorScheme = when (darkThemeMode) {
        DarkThemeMode.Dark -> DarkColorScheme
        DarkThemeMode.Light -> LightColorScheme
        DarkThemeMode.System -> {
            if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
