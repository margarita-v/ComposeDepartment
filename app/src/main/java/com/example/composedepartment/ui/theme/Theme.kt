package com.example.composedepartment.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.composedepartment.ui.theme.custom.ColorsCustom
import com.example.composedepartment.ui.theme.custom.MaterialThemeCustom

private val DarkColorPalette = darkColors(
    primary = BlueLighter,
    background = BlackBackground,
    surface = BlackBackground,
    onPrimary = BlackPrimary,
    onSecondary = BlackPrimary,
    onError = BlackPrimary
)

private val LightColorPalette = lightColors(
    primary = BlueDarker,
    onSecondary = BlackPrimary,
    onBackground = BlackPrimary,
    onSurface = BlackPrimary
)

private val DarkColorCustomPalette = ColorsCustom(
    orange = OrangeLighter,
    green = GreenLighter,
    pink = PinkLighter
)

private val LightColorCustomPalette = ColorsCustom(
    orange = OrangeDarker,
    green = GreenDarker,
    pink = PinkDarker
)

@Composable
fun ComposeDepartmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val colorsCustom = if (darkTheme) {
        DarkColorCustomPalette
    } else {
        LightColorCustomPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    MaterialThemeCustom(colors = colorsCustom)
}