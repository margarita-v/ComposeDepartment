package com.example.composedepartment.ui.department.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composedepartment.ui.base.components.NavigationTopBarActionData
import com.example.composedepartment.ui.base.components.NavigationTopBarView

@Composable
internal fun DetailsCommonContainer(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    actionData: NavigationTopBarActionData? = null,
    content: @Composable LazyItemScope.() -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.systemBarsPadding()) {
            NavigationTopBarView(
                onNavigationClicked = onBackClicked,
                actionData = actionData
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    content()
                }
            }
        }
    }
}