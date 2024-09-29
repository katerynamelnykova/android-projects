package com.katerynamelnykova.lab6.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SnakeColorScheme = darkColorScheme(
    primary = DarkGreen,
    secondary = DarkGreen,
    tertiary = DarkGreen,
    background = LightGreen,
    surface = White,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = LightGreen,
    onSurface = LightGreen,
)

@Composable
fun LabsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = SnakeColorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}