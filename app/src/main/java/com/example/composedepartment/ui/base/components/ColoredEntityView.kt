package com.example.composedepartment.ui.base.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedepartment.domain.ColoredEntity
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.utils.toColor
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ColoredEntityView(
    data: List<ColoredEntity>,
    modifier: Modifier = Modifier,
    spacing: Dp = 4.dp,
    verticalTextPadding: Dp = 2.dp,
    horizontalTextPadding: Dp = 8.dp,
    textStyle: TextStyle = MaterialTheme.typography.overline
) {
    FlowRow(
        modifier = modifier,
        mainAxisSpacing = spacing,
        crossAxisSpacing = spacing
    ) {
        data.forEach { item ->
            val skillColor = item.color.toColor()
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(skillColor.copy(alpha = 0.12f))
            ) {
                Text(
                    modifier = Modifier.padding(
                        vertical = verticalTextPadding,
                        horizontal = horizontalTextPadding
                    ),
                    text = item.name,
                    style = textStyle.copy(color = skillColor)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ColoredEntityPreview() {
    ComposeDepartmentTheme {
        Surface {
            ColoredEntityView(
                data = Employees.employees.first().skills
            )
        }
    }
}