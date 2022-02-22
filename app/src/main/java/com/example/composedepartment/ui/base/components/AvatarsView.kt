package com.example.composedepartment.ui.base.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.theme.BlackPrimary
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.LightBackground

@Composable
fun AvatarsView(
    photos: List<Int>,
    align: AvatarsAlign,
    modifier: Modifier = Modifier,
    restCount: Int = 0,
    size: Dp = 34.dp,
    spaceBetween: Int = 10
) {
    Row(modifier = modifier.wrapContentWidth()) {
        photos.forEachIndexed { index, photo ->
            val offset = when (align) {
                AvatarsAlign.Start -> -index * spaceBetween
                AvatarsAlign.End -> {
                    when {
                        index == 0 -> spaceBetween * 2
                        index < photos.lastIndex -> index * spaceBetween
                        else -> 0
                    }
                }
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
        if (restCount > 0) {
            val restOffset = when(align) {
                AvatarsAlign.Start -> -(photos.lastIndex + 1) * spaceBetween
                AvatarsAlign.End -> 0
            }
            Box(
                modifier = Modifier
                    .offset(x = restOffset.dp)
                    .size(size = size)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .background(LightBackground)
            ) {
                Text(
                    text = "+$restCount",
                    modifier = Modifier.align(alignment = Alignment.Center),
                    style = MaterialTheme.typography.subtitle1,
                    color = BlackPrimary
                )
            }
        }
    }
}

enum class AvatarsAlign {
    Start,
    End
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AvatarsPreview() {
    ComposeDepartmentTheme {
        AvatarsView(
            photos = Projects.leads.map { it.photo },
            align = AvatarsAlign.Start,
            restCount = 4
        )
    }
}