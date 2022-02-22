package com.example.composedepartment.ui.department.tabs

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.ui.base.components.ColoredEntityView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme

@ExperimentalMaterialApi
@Composable
internal fun Employees(
    employees: List<Employee>,
    onEmployeeClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        employees.forEach { employee ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    elevation = 10.dp,
                    onClick = { onEmployeeClick(employee.id) }
                ) {
                    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
                        val (name, photo, job, skills) = createRefs()
                        Text(
                            modifier = Modifier.constrainAs(name) {
                                top.linkTo(parent.top)
                            },
                            text = employee.name,
                            style = MaterialTheme.typography.h6
                        )
                        Image(
                            modifier = Modifier.constrainAs(photo) {
                                top.linkTo(name.bottom, margin = 12.dp)
                            },
                            painter = painterResource(id = employee.photo),
                            contentDescription = "${employee.name}'s photo"
                        )
                        Text(
                            modifier = Modifier.constrainAs(job) {
                                top.linkTo(name.bottom, margin = 12.dp)
                                start.linkTo(photo.end, margin = 16.dp)
                            },
                            text = employee.job,
                            style = MaterialTheme.typography.subtitle2
                        )
                        ColoredEntityView(
                            data = employee.skills,
                            modifier = Modifier.constrainAs(skills) {
                                top.linkTo(job.bottom, margin = 8.dp)
                                start.linkTo(photo.end, margin = 16.dp)
                                end.linkTo(parent.end, margin = 16.dp)
                                width = Dimension.fillToConstraints
                            }
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
private fun EmployeesPreview() {
    ComposeDepartmentTheme {
        Surface {
            Employees(
                employees = com.example.composedepartment.interactor.Employees.employees
            )
        }
    }
}