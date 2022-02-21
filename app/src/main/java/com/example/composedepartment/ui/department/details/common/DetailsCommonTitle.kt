package com.example.composedepartment.ui.department.details.common

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
internal fun DetailsCommonTitle(@StringRes titleRes: Int, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(id = titleRes),
        style = MaterialTheme.typography.h4.copy(
            fontWeight = FontWeight.Bold
        )
    )
}