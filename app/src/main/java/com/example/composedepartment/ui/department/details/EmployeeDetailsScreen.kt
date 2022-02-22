package com.example.composedepartment.ui.department.details

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
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
import com.example.composedepartment.domain.Gender
import com.example.composedepartment.domain.Project
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.components.NavigationTopBarActionData
import com.example.composedepartment.ui.base.components.ProjectFirstLetterView
import com.example.composedepartment.ui.base.components.SkillsView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom
import com.example.composedepartment.ui.department.details.common.DetailsCommonContainer
import com.example.composedepartment.ui.department.details.common.DetailsCommonInfo
import com.example.composedepartment.ui.utils.pluralResource

@ExperimentalMaterialApi
@Composable
fun EmployeeDetailsScreen(
    employee: Employee,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onCallClicked: (String) -> Unit,
    onProjectClicked: (String) -> Unit
) {
    DetailsCommonContainer(
        onBackClicked = onBackClicked,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
        actionData = NavigationTopBarActionData(
            contentDescription = "Call",
            iconResId = R.drawable.ic_call,
            onActionClicked = { onCallClicked(employee.phone) }
        )
    ) {
        EmployeeHeader(employee, onCallClicked)
        //todo bottomsheet
        EmployeeInfo(employee, onProjectClicked)
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

@ExperimentalMaterialApi
@Composable
private fun EmployeeInfo(employee: Employee, onProjectClicked: (String) -> Unit) {
    DetailsCommonInfo(titleRes = R.string.employee_title) {
        AboutEmployee(employee)
        AboutProjects(
            employee = employee,
            project = Projects.projects.firstOrNull { it.id == employee.projectId },
            onProjectClicked = onProjectClicked
        )
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

@ExperimentalMaterialApi
@Composable
private fun AboutProjects(
    employee: Employee,
    project: Project?,
    onProjectClicked: (String) -> Unit
) {
    project?.also {
        Card(
            onClick = { onProjectClicked(it.id) },
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp
        ) {
            ConstraintLayout(modifier = Modifier.padding(16.dp)) {
                val (icon, name, description, arrow) = createRefs()
                ProjectFirstLetterView(
                    project = it,
                    modifier = Modifier.constrainAs(icon) {
                        start.linkTo(parent.start)
                    }
                )
                Text(
                    text = it.name,
                    modifier = Modifier.constrainAs(name) {
                        start.linkTo(icon.end, margin = 18.dp)
                    },
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(id = R.string.employee_project_description),
                    modifier = Modifier.constrainAs(description) {
                        start.linkTo(icon.end, margin = 18.dp)
                        top.linkTo(name.bottom, 8.dp)
                    },
                    style = MaterialTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "to project",
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier.constrainAs(arrow) {
                        end.linkTo(parent.end)
                        centerVerticallyTo(parent)
                    }
                )
            }
        }
    }
    val experienceText =
        stringResource(
            id = R.string.employee_experience_male
                .takeIf { employee.gender == Gender.MALE }
                ?: R.string.employee_experience_female
        ) + pluralResource(
            resId = R.plurals.projects,
            quantity = employee.previousProjectCount,
            employee.previousProjectCount
        )
    Text(
        text = experienceText,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialThemeCustom.colors.grayTab,
        modifier = Modifier.padding(top = 24.dp)
    )
    Divider(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
    )
    if (employee.skills.isNotEmpty()) {
        Text(
            text = stringResource(id = R.string.employee_skills_title),
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 32.dp)
        )
        SkillsView(
            skills = employee.skills,
            modifier = Modifier.padding(top = 16.dp),
            textStyle = MaterialTheme.typography.caption
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

@ExperimentalMaterialApi
@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EmployeeDetailsPreview() {
    ComposeDepartmentTheme {
        Surface {
            EmployeeDetailsScreen(
                employee = Employees.employees.last(),
                onBackClicked = {},
                onCallClicked = {},
                onProjectClicked = {}
            )
        }
    }
}