package com.example.composedepartment.ui.base.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.domain.Project
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.utils.toColor

@Composable
fun ProjectFirstLetterView(project: Project, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                project.color.toColor(),
                RoundedCornerShape(
                    topEnd = 12.dp,
                    bottomEnd = 12.dp,
                    bottomStart = 12.dp
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = project.name.firstOrNull()?.toString() ?: "N",
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProjectFirstLetterPreview() {
    ComposeDepartmentTheme {
        ProjectFirstLetterView(
            project = Projects.projects.first()
        )
    }
}