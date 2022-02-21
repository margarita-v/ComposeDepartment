package com.example.composedepartment.ui.base.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.R
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme

@Composable
fun NavigationTopBarView(
    onNavigationClicked: () -> Unit,
    actionData: NavigationTopBarActionData? = null
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NavigationButton(contentDescription = "Back", icon = R.drawable.ic_arrow_left) {
                onNavigationClicked()
            }
            actionData?.also {
                NavigationButton(contentDescription = it.contentDescription, icon = it.iconResId) {
                    it.onActionClicked()
                }
            }
        }
    }
}

@Immutable
data class NavigationTopBarActionData(
    val contentDescription: String?,
    @DrawableRes val iconResId: Int,
    val onActionClicked: () -> Unit
)

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NavigationTopBarPreview() {
    ComposeDepartmentTheme {
        NavigationTopBarView(
            onNavigationClicked = {},
            actionData = NavigationTopBarActionData(
                contentDescription = null,
                iconResId = R.drawable.ic_search,
                onActionClicked = {}
            )
        )
    }
}
