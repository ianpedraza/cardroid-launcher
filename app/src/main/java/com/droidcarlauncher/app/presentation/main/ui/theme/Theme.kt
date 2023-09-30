package com.droidcarlauncher.app.presentation.main.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.droidcarlauncher.app.domain.model.settings.appearance.darkheme.DarkThemeMode

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color.White.apply { },
)

@Composable
fun DroidcarLauncherTheme(
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
