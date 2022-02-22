package com.example.composedepartment.ui.department.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composedepartment.R
import com.example.composedepartment.domain.Project
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.components.AvatarsAlign
import com.example.composedepartment.ui.base.components.AvatarsView
import com.example.composedepartment.ui.base.components.ProjectFirstLetterView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom
import com.example.composedepartment.ui.department.details.common.DetailsCommonContainer
import com.example.composedepartment.ui.department.details.common.DetailsCommonInfo
import com.example.composedepartment.ui.department.details.common.DetailsCommonTitle
import com.example.composedepartment.ui.utils.pluralResource

//todo animation on scroll
@Composable
fun ProjectDetailsScreen(
    project: Project,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    DetailsCommonContainer(
        onBackClicked = onBackClicked,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp)) {
            Card(
                backgroundColor = Color.White,
                elevation = 10.dp
            ) {
                ProjectFirstLetterView(
                    project = project,
                    modifier = Modifier.padding(horizontal = 34.dp, vertical = 16.dp)
                )
            }
            Text(
                text = project.name,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        ProjectInfo(project = project)
    }
}

@Composable
private fun ProjectInfo(project: Project) {
    DetailsCommonInfo(titleRes = R.string.project_title) {
        Text(
            text = project.description,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(top = 16.dp)
        )
        Divider(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        )
        DetailsCommonTitle(
            titleRes = R.string.project_team,
            modifier = Modifier.padding(top = 32.dp)
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable {} // route to team
        ) {
            AvatarsView(
                photos = project.leads.map { it.photo },
                align = AvatarsAlign.Start,
                restCount = project.members.size,
                size = 40.dp
            )
            Text(
                text = stringResource(id = R.string.project_all) +
                        pluralResource(
                            resId = R.plurals.members,
                            quantity = project.members.size + project.leads.size,
                            project.members.size
                        ),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialThemeCustom.colors.grayTab,
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "to team",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }
        Divider(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        ) {
            ProjectDaysCounter(days = project.days)
            DetailsCommonTitle(
                titleRes = R.string.project_days,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

@Composable
private fun ProjectDaysCounter(days: Int) {
    ConstraintLayout(modifier = Modifier.defaultMinSize(minWidth = 52.dp)) {
        val (count, description) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(MaterialTheme.colors.background)
                .constrainAs(count) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(
                text = days.toString(),
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialThemeCustom.colors.orange,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .align(alignment = Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .constrainAs(description) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    top.linkTo(count.bottom)
                }
        ) {
            Text(
                text = pluralResource(resId = R.plurals.days, quantity = days, days),
                style = MaterialTheme.typography.subtitle2.copy(
                    color = MaterialThemeCustom.colors.orange
                ),
                maxLines = 1,
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .align(alignment = Alignment.Center),
            )
        }
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