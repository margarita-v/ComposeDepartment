package com.example.composedepartment.ui.department.tabs

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedepartment.R
import com.example.composedepartment.domain.Project
import com.example.composedepartment.ui.base.components.AvatarsAlign
import com.example.composedepartment.ui.base.components.AvatarsView
import com.example.composedepartment.ui.base.components.ProjectFirstLetterView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom
import com.example.composedepartment.ui.utils.pluralResource
import com.example.composedepartment.ui.utils.pressedState

@ExperimentalMaterialApi
@Composable
internal fun Projects(
    projects: List<Project>,
    onProjectClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        projects.forEach { project ->
            item {
                val (interactionSource, padding) = pressedState()
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .pressedState(padding),
                    elevation = 10.dp,
                    interactionSource = interactionSource,
                    onClick = { onProjectClick(project.id) }
                ) {
                    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
                        val (icon, members, name, leads) = createRefs()
                        ProjectFirstLetterView(
                            project = project,
                            modifier = Modifier.constrainAs(icon) {
                                top.linkTo(parent.top)
                            }
                        )
                        AvatarsView(
                            photos = project.leads.map { it.photo },
                            align = AvatarsAlign.End,
                            modifier = Modifier.constrainAs(leads) {
                                end.linkTo(parent.end)
                            }
                        )
                        Text(
                            modifier = Modifier.constrainAs(members) {
                                top.linkTo(icon.bottom, margin = 18.dp)
                            },
                            text = pluralResource(
                                resId = R.plurals.members,
                                quantity = project.members.size,
                                project.members.size
                            ),
                            style = MaterialTheme.typography.caption,
                            color = MaterialThemeCustom.colors.grayTab
                        )
                        Text(
                            text = project.name,
                            modifier = Modifier.constrainAs(name) {
                                top.linkTo(members.bottom, margin = 6.dp)
                                bottom.linkTo(parent.bottom)
                            },
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.subtitle1
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
private fun ProjectsPreview() {
    ComposeDepartmentTheme {
        Surface {
            Projects(
                projects = com.example.composedepartment.interactor.Projects.projects,
                onProjectClick = {}
            )
        }
    }
}