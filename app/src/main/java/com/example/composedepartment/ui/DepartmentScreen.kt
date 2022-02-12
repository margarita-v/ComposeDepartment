package com.example.composedepartment.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedepartment.R
import com.example.composedepartment.ui.theme.ComposeDepartmentTheme

@Composable
fun DepartmentScreen(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    onSearchClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.systemBarsPadding()) {
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
            Text(text = "Hello Main!")
        }
    }
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