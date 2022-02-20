package com.example.composedepartment.ui.department.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedepartment.R
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.ui.base.components.NavigationTopBarActionData
import com.example.composedepartment.ui.base.components.NavigationTopBarView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme

@Composable
fun EmployeeDetails(
    employee: Employee,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onCallClicked: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.systemBarsPadding()) {
            NavigationTopBarView(
                onNavigationClicked = onBackClicked,
                actionData = NavigationTopBarActionData(
                    contentDescription = "Call",
                    iconResId = R.drawable.ic_call,
                    onActionClicked = onCallClicked
                )
            )
            Text(employee.name)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DepartmentScreenPreview() {
    ComposeDepartmentTheme {
        Surface {
            EmployeeDetails(
                employee = Employees.employees.first(),
                onBackClicked = {},
                onCallClicked = {}
            )
        }
    }
}