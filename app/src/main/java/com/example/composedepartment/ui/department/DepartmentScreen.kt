package com.example.composedepartment.ui.department

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.domain.Project
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme

@ExperimentalMaterialApi
@Composable
fun DepartmentScreen(
    modifier: Modifier = Modifier,
    employees: List<Employee> = listOf(),
    projects: List<Project> = listOf(),
    onSearchClick: () -> Unit = {},
    onDarkThemeToggle: (Boolean) -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.systemBarsPadding()) {
            Spacer(modifier = Modifier.size(8.dp))
            DepartmentAppBar(onSearchClick = onSearchClick)
            DepartmentPager(
                employees = employees,
                projects = projects,
                onDarkThemeToggle = onDarkThemeToggle
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DepartmentScreenPreview() {
    ComposeDepartmentTheme {
        Surface {
            DepartmentScreen(modifier = Modifier.fillMaxSize())
        }
    }
}