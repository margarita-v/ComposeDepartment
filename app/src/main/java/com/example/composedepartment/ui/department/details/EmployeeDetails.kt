package com.example.composedepartment.ui.department.details

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.R
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.ui.base.components.NavigationTopBarActionData
import com.example.composedepartment.ui.base.components.NavigationTopBarView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom
import com.example.composedepartment.ui.utils.pluralResource

@Composable
fun EmployeeDetails(
    employee: Employee,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onCallClicked: (String) -> Unit
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
                    onActionClicked = { onCallClicked(employee.phone) }
                )
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    EmployeeHeader(employee, onCallClicked)
                }
            }
        }
    }
}

@Composable
private fun EmployeeHeader(employee: Employee, onCallClicked: (String) -> Unit) {
    Image(
        modifier = Modifier
            .padding(top = 24.dp)
            .size(112.dp)
            .clip(CircleShape),
        painter = painterResource(id = employee.photo),
        contentDescription = "${employee.name}'s photo"
    )
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = employee.name.replace(' ', '\n'),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h4
    )
    TextButton(onClick = { onCallClicked(employee.phone) }) {
        Text(
            text = employee.phone,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colors.primary
        )
    }
    val age = pluralResource(R.plurals.years, employee.age, employee.age)
        .takeIf { employee.age > 0 }
        .orEmpty()
    val city = ", ${employee.city}"
        .takeIf { employee.city.isNotBlank() }
        .orEmpty()
    (age + city).takeIf { it.isNotBlank() }?.also {
        Text(
            text = it,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialThemeCustom.colors.grayTab
        )
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