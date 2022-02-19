package com.example.composedepartment.ui.base.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    contentDescription: String,
    @DrawableRes icon: Int,
    onClicked: () -> Unit
) {
    IconButton(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .size(40.dp)
            .background(MaterialTheme.colors.surface),
        onClick = { onClicked() }
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = MaterialTheme.colors.onSurface,
            contentDescription = contentDescription
        )
    }
}