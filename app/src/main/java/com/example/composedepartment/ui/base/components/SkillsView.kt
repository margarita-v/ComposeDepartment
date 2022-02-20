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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.domain.Skill
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.utils.toColor
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun SkillsView(skills: List<Skill>, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier,
        mainAxisSpacing = 4.dp,
        crossAxisSpacing = 4.dp
    ) {
        skills.forEach { skill ->
            val skillColor = skill.color.toColor()
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(skillColor.copy(alpha = 0.12f))
            ) {
                Text(
                    modifier = Modifier.padding(
                        vertical = 2.dp,
                        horizontal = 8.dp
                    ),
                    text = skill.name,
                    style = MaterialTheme.typography.overline.copy(
                        color = skillColor
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SkillsPreview() {
    ComposeDepartmentTheme {
        Surface {
            SkillsView(
                skills = Employees.employees.first().skills
            )
        }
    }
}