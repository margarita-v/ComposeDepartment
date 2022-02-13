package com.example.composedepartment.ui.department

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composedepartment.R

@Composable
internal fun DepartmentAppBar(onSearchClick: () -> Unit = {}) {
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
