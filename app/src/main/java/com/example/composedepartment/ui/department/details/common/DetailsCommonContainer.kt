package com.example.composedepartment.ui.department.details.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composedepartment.ui.base.components.NavigationTopBarActionData
import com.example.composedepartment.ui.base.components.NavigationTopBarView

@ExperimentalMaterialApi
@Composable
internal fun DetailsCommonContainer(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    actionData: NavigationTopBarActionData? = null,
    header: @Composable ColumnScope.() -> Unit,
    info: @Composable () -> Unit
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        BackdropScaffold(
            modifier = modifier.systemBarsPadding(),
            scaffoldState = scaffoldState,
            backLayerBackgroundColor = Color.Unspecified,
            frontLayerScrimColor = Color.Unspecified,
            appBar = {
                NavigationTopBarView(
                    onNavigationClicked = onBackClicked,
                    actionData = actionData
                )
            },
            backLayerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    horizontalAlignment = horizontalAlignment
                ) {
                    header()
                }
            },
            frontLayerElevation = 10.dp,
            frontLayerContent = { info() }
        )
    }
}