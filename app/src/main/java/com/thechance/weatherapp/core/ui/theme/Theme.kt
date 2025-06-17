package com.thechance.weatherapp.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkPurple,
    secondary = BabyBlue,
    surface = DarkPurple,
    onSurface = Color.White,
    background = DarkPurple,
    onBackground = Color.White,
    surfaceVariant = DarkPurpleBlur,
    surfaceContainer = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = BabyBlue,
    secondary = DarkPurple,
    surface = Color.White,
    onSurface = DarkPurple,
    background = BabyBlue,
    onBackground = DarkPurple,
    surfaceVariant = BabyBlueBlur,
    surfaceContainer = DarkPurple,
)

@Composable
fun WeatherAppTheme(
    isNight: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isNight -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}