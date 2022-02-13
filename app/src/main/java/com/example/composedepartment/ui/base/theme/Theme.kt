package com.example.composedepartment.ui.base.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composedepartment.ui.base.theme.custom.ColorsCustom
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom

private val DarkColorPalette = darkColors(
    primary = BlueLighter,
    background = BlackBackground,
    surface = BlackSurface,

    onPrimary = BlackPrimary,
    onSecondary = BlackPrimary,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = BlackPrimary
)

private val LightColorPalette = lightColors(
    primary = BlueDarker,
    background = LightBackground,
    surface = Color.White,

    onPrimary = Color.White,
    onSecondary = BlackPrimary,
    onBackground = BlackPrimary,
    onSurface = BlackPrimary,
    onError = Color.White
)

private val DarkColorCustomPalette = ColorsCustom(
    blue = BlueLighter,
    gray = Color.White,
    orange = OrangeLighter,
    green = GreenLighter,
    pink = PinkLighter,
    splash = BlackBackground
)

private val LightColorCustomPalette = ColorsCustom(
    blue = BlueDarker,
    gray = GrayLabel,
    orange = OrangeDarker,
    green = GreenDarker,
    pink = PinkDarker,
    splash = SplashBackground
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