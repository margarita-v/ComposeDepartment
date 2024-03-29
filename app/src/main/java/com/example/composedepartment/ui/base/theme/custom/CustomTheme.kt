package com.example.composedepartment.ui.base.theme.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

class ColorsCustom(
    val blue: Color,
    val gray: Color,
    val grayTab: Color,
    val orange: Color,
    val green: Color,
    val pink: Color,
    val splash: Color
)

object LocalMaterialThemeCustom {
    var colors: ColorsCustom? = null
}

@Composable
fun MaterialThemeCustom(colors: ColorsCustom) {
    LocalMaterialThemeCustom.colors = colors
}

object MaterialThemeCustom {

    val colors: ColorsCustom
        @Composable
        @ReadOnlyComposable
        get() {
            return if (LocalMaterialThemeCustom.colors == null) {
                throw RuntimeException("You must initialize MaterialThemeCustom colors")
            } else {
                LocalMaterialThemeCustom.colors!!
            }
        }
}