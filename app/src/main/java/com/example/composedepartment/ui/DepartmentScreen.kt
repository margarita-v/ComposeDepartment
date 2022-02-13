package com.example.composedepartment.ui

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.composedepartment.R
import com.example.composedepartment.ui.theme.ComposeDepartmentTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun DepartmentScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.systemBarsPadding()) {
            Spacer(modifier = Modifier.size(8.dp))
            DepartmentAppBar(onSearchClick = onSearchClick)
            Spacer(modifier = Modifier.size(16.dp))
            DepartmentContent()
        }
    }
}

@Composable
private fun DepartmentAppBar(onSearchClick: () -> Unit = {}) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.size(width = 58.dp, height = 40.dp),
                painter = painterResource(id = R.drawable.ic_surf),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = null,
            )
            IconButton(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(40.dp)
                    .background(MaterialTheme.colors.surface),
                onClick = { onSearchClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = "Search"
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalUnitApi::class)
@Composable
private fun DepartmentContent(modifier: Modifier = Modifier) {
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
            Tab(
                text = {
                    Text(
                        text = title.uppercase(Locale.getDefault()),
                        style = TextStyle(
                            color = MaterialTheme.colors.primary,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = TextUnit(0.1f, TextUnitType.Em),
                            fontSize = 14.sp
                        )
                    )
                },
                selected = pagerState.currentPage == index,
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
        Text(text = "Hello $page!")
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

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DepartmentScreenPreview() {
    ComposeDepartmentTheme {
        Surface {
            DepartmentScreen(modifier = Modifier.fillMaxSize())
        }
    }
}