package com.example.composedepartment.ui.department.search

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.R
import com.example.composedepartment.domain.ColoredEntity
import com.example.composedepartment.domain.Project
import com.example.composedepartment.ui.base.components.ColoredEntityView
import com.example.composedepartment.ui.base.components.ProjectFirstLetterView
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.utils.pluralResource

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onProjectClick: (String) -> Unit
) {
    val projects by viewModel.projects.collectAsState()
    val departments by viewModel.departments.collectAsState()
    val skills by viewModel.skills.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.systemBarsPadding()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    if (projects.isNotEmpty()) {
                        Title(titleResId = R.string.search_projects)
                        Projects(projects = projects, onProjectClick = onProjectClick)
                    }
                    if (departments.isNotEmpty()) {
                        Title(titleResId = R.string.search_departments)
                        Entities(data = departments)
                    }
                    if (skills.isNotEmpty()) {
                        Title(titleResId = R.string.search_skills)
                        Entities(data = skills)
                    }
                }
            }
        }
    }
}

@Composable
private fun Title(@StringRes titleResId: Int) {
    Text(
        text = stringResource(id = titleResId),
        style = MaterialTheme.typography.h4.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(top = 32.dp, start = 20.dp, end = 20.dp)
    )
}

@Composable
private fun Entities(data: List<ColoredEntity>) {
    ColoredEntityView(
        data = data,
        spacing = 8.dp,
        verticalTextPadding = 4.dp,
        horizontalTextPadding = 12.dp,
        modifier = Modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp),
        textStyle = MaterialTheme.typography.caption
    )
}

@ExperimentalMaterialApi
@Composable
private fun Projects(projects: List<Project>, onProjectClick: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        projects.forEach { project ->
            item {
                Card(
                    modifier = Modifier.size(width = 138.dp, height = 156.dp),
                    elevation = 10.dp,
                    onClick = { onProjectClick(project.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ProjectFirstLetterView(project = project)
                        Text(
                            text = pluralResource(
                                resId = R.plurals.members,
                                quantity = project.peopleCount,
                                project.peopleCount
                            ),
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 18.dp)
                        )
                        Text(
                            text = project.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 6.dp)
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
private fun SearchPreview() {
    ComposeDepartmentTheme {
        Surface {
            SearchScreen {}
        }
    }
}