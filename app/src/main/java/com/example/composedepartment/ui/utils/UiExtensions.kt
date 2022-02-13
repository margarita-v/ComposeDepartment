package com.example.composedepartment.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composedepartment.domain.EntityColor
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom

@Composable
fun EntityColor.toColor(): Color = when (this) {
    EntityColor.BLUE -> MaterialThemeCustom.colors.blue
    EntityColor.ORANGE -> MaterialThemeCustom.colors.orange
    EntityColor.GREEN -> MaterialThemeCustom.colors.green
    EntityColor.PINK -> MaterialThemeCustom.colors.pink
    EntityColor.GRAY -> MaterialThemeCustom.colors.gray
}