package com.example.composedepartment.ui.department.details

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.example.composedepartment.R
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.ui.base.components.NavigationTopBarActionData
import com.example.composedepartment.ui.base.components.NavigationTopBarView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom
import com.example.composedepartment.ui.utils.pluralResource

@Composable
fun EmployeeDetailsScreen(
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
                    EmployeeInfo(employee)
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

@Composable
private fun EmployeeInfo(employee: Employee) {
    Surface(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 10.dp,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {
            Text(
                text = stringResource(id = R.string.employee_title),
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            AboutEmployee(employee)
        }
    }
}

@Composable
private fun AboutEmployee(employee: Employee) {
    ConstraintLayout(
        modifier = Modifier
            .padding(top = 22.dp)
            .fillMaxWidth()
    ) {
        val (jobTitle, experienceTitle, departmentTitle) = createRefs()
        val (jobValue, experienceValue, departmentValue) = createRefs()
        // titles
        Text(
            modifier = Modifier.constrainAs(jobTitle) {
                start.linkTo(parent.start)
            },
            text = stringResource(id = R.string.employee_job),
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.constrainAs(experienceTitle) {
                start.linkTo(parent.start)
                top.linkTo(jobTitle.bottom, margin = 16.dp)
            },
            text = stringResource(id = R.string.employee_experience),
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.constrainAs(departmentTitle) {
                start.linkTo(parent.start)
                top.linkTo(experienceTitle.bottom, margin = 16.dp)
            },
            text = stringResource(id = R.string.employee_department),
            style = MaterialTheme.typography.subtitle2
        )
        val titlesBarrier =
            createEndBarrier(jobTitle, experienceTitle, departmentTitle, margin = 8.dp)
        // values
        EmployeeValue(
            text = employee.job,
            selfRef = jobValue,
            centerRef = jobTitle,
            barrier = titlesBarrier
        )
        val years = employee.experienceMonths / 12
        val months = employee.experienceMonths.mod(12)
        val monthsString = stringResource(id = R.string.employee_month_short, months)
        val experience = when {
            years > 0 -> stringResource(id = R.string.employee_year_short, years) +
                    " $monthsString".takeIf { months > 0 }.orEmpty()
            months > 0 -> monthsString
            else -> stringResource(id = R.string.employee_no_experience)
        }
        EmployeeValue(
            text = experience,
            selfRef = experienceValue,
            centerRef = experienceTitle,
            barrier = titlesBarrier
        )
        EmployeeValue(
            text = employee.department,
            selfRef = departmentValue,
            centerRef = departmentTitle,
            barrier = titlesBarrier,
            style = MaterialTheme.typography.h5.copy(
                color = MaterialTheme.colors.primary
            )
        )
    }
}

@Composable
private fun ConstraintLayoutScope.EmployeeValue(
    text: String,
    selfRef: ConstrainedLayoutReference,
    centerRef: ConstrainedLayoutReference,
    barrier: ConstraintLayoutBaseScope.VerticalAnchor,
    style: TextStyle = MaterialTheme.typography.h5
) {
    Text(
        modifier = Modifier.constrainAs(selfRef) {
            width = Dimension.fillToConstraints
            start.linkTo(barrier)
            end.linkTo(parent.end)
            centerVerticallyTo(centerRef)
        },
        textAlign = TextAlign.End,
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        style = style
    )
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DepartmentScreenPreview() {
    ComposeDepartmentTheme {
        Surface {
            EmployeeDetailsScreen(
                employee = Employees.employees.first(),
                onBackClicked = {},
                onCallClicked = {}
            )
        }
    }
}