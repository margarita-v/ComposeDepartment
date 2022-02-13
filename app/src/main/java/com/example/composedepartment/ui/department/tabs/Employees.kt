package com.example.composedepartment.ui.department.tabs

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.department.DepartmentScreen

@ExperimentalMaterialApi
@Composable
internal fun Employees(
    employees: List<Employee>,
    onEmployeeClick: (Employee) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            horizontal = 24.dp
        )
    ) {
        employees.forEach { employee ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onClick = { onEmployeeClick(employee) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = employee.name,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
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
            DepartmentScreen(
                modifier = Modifier.fillMaxSize(),
                employees = com.example.composedepartment.interactor.Employees.employees
            )
        }
    }
}