package com.example.composedepartment.ui.department.details

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedepartment.domain.Project
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme

@Composable
fun ProjectDetailsScreen(
    project: Project,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    DetailsCommonContainer(onBackClicked = onBackClicked, modifier = modifier) {
        Text(project.name)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProjectDetailsPreview() {
    ComposeDepartmentTheme {
        Surface {
            ProjectDetailsScreen(
                project = Projects.projects.first(),
                onBackClicked = {}
            )
        }
    }
}