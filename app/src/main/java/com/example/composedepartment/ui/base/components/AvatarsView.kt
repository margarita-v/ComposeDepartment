package com.example.composedepartment.ui.base.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme

@Composable
fun AvatarsView(
    photos: List<Int>,
    modifier: Modifier = Modifier,
    restCount: Int = 0,
    size: Dp = 34.dp,
    spaceBetween: Int = 10
) {
    Row(modifier = modifier.wrapContentWidth()) {
        photos.forEachIndexed { index, photo ->
            val offset = when {
                index == 0 -> spaceBetween * 2
                index < photos.lastIndex -> index * spaceBetween
                else -> 0
            }
            Image(
                modifier = Modifier
                    .offset(x = offset.dp)
                    .size(size = size)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                painter = painterResource(id = photo),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AvatarsPreview() {
    ComposeDepartmentTheme {
        AvatarsView(
            photos = Projects.leads.map { it.photo }
        )
    }
}