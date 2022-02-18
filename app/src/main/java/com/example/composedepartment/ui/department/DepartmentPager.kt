package com.example.composedepartment.ui.department

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.composedepartment.LocalDarkThemeEnabled
import com.example.composedepartment.R
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.domain.Project
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom
import com.example.composedepartment.ui.department.tabs.About
import com.example.composedepartment.ui.department.tabs.Employees
import com.example.composedepartment.ui.department.tabs.Projects
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalMaterialApi
@OptIn(ExperimentalPagerApi::class, ExperimentalUnitApi::class)
@Composable
internal fun DepartmentPager(
    modifier: Modifier = Modifier,
    employees: List<Employee>,
    projects: List<Project>,
    onDarkThemeToggle: (Boolean) -> Unit = {}
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val pages = context.resources.getStringArray(R.array.department_tabs)

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        edgePadding = 20.dp,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .padding(horizontal = 16.dp),
                height = 3.dp,
                color = MaterialTheme.colors.primary
            )
        }
    ) {
        pages.forEachIndexed { index, title ->
            val isSelected = pagerState.currentPage == index
            val tabTextColor by animateColorAsState(
                targetValue = if (isSelected) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialThemeCustom.colors.grayTab
                },
                animationSpec = tween(durationMillis = 100, easing = LinearEasing)
            )
            Tab(
                text = {
                    Text(
                        text = title.uppercase(Locale.getDefault()),
                        style = TextStyle(
                            color = tabTextColor,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = TextUnit(0.1f, TextUnitType.Em),
                            fontSize = 14.sp
                        )
                    )
                },
                selected = isSelected,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }

    HorizontalPager(
        count = pages.size,
        modifier = modifier.fillMaxSize(),
        state = pagerState,
    ) { page ->
        when (page) {
            DepartmentTab.EMPLOYEES.ordinal -> Employees(employees) {} //todo to employee screen
            DepartmentTab.PROJECTS.ordinal -> Projects(projects) {} // todo to project screen
            DepartmentTab.ABOUT.ordinal -> About(
                {},
                {},
                isDarkTheme = LocalDarkThemeEnabled.current,
                onDarkThemeToggle = onDarkThemeToggle
            ) // todo handle routes
        }
    }
}

/**
 * Experimental modifier which makes the indicator width constant
 * @see Modifier.tabIndicatorOffset
 */
private fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val indicatorWidth = 24.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}
