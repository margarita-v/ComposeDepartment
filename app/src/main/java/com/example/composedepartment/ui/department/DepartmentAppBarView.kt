package com.example.composedepartment.ui.department

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.R
import com.example.composedepartment.ui.base.components.NavigationButton
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme

@Composable
internal fun DepartmentAppBarView(onSearchClick: () -> Unit = {}) {
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
            NavigationButton(contentDescription = "Search", icon = R.drawable.ic_search) {
                onSearchClick()
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DepartmentAppBarPreview() {
    ComposeDepartmentTheme {
        DepartmentAppBarView()
    }
}
