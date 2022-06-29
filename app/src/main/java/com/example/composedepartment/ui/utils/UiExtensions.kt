package com.example.composedepartment.ui.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

@Composable
fun pressedState(paddingValue: Float = 4f): Pair<MutableInteractionSource, Dp> {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val padding by animateFloatAsState(targetValue = if (isPressed) paddingValue else 0f)
    return interactionSource to padding.dp
}

@Stable
fun Modifier.pressedState(
    padding: Dp,
    start: Boolean = true,
    end: Boolean = true,
    top: Boolean = false,
    bottom: Boolean = false,
) =
    padding(
        start = if (start) padding else 0.dp,
        end = if (end) padding else 0.dp,
        top = if (top) padding else 0.dp,
        bottom = if (bottom) padding else 0.dp
    )