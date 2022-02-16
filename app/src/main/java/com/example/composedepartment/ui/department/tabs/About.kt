package com.example.composedepartment.ui.department.tabs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedepartment.R
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.DarkSwitch
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom

@Composable
internal fun About(
    onChatClicked: () -> Unit = {},
    onGithubClicked: () -> Unit = {},
    isDarkTheme: Boolean,
    onDarkThemeToggle: (Boolean) -> Unit = {}
) {
    var isChecked by remember { mutableStateOf(isDarkTheme) }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_android_dark),
                tint = MaterialThemeCustom.colors.grayTab,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2.copy(
                    color = MaterialThemeCustom.colors.grayTab
                ),
                text = stringResource(id = R.string.about_description_text)
            )
            OutlinedButton(onClick = { onGithubClicked() }) {
                Text(
                    text = stringResource(id = R.string.go_to_github_text),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle2,
                    text = stringResource(R.string.enable_dark_theme_text)
                )
                Switch(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        onDarkThemeToggle(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = DarkSwitch,
                        checkedTrackColor = Color.Black,
                        checkedTrackAlpha = 1f,
                        uncheckedThumbColor = MaterialTheme.colors.primary,
                        uncheckedTrackColor = MaterialTheme.colors.primary,
                    )
                )
            }
            Button(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 24.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = { onChatClicked() }
            ) {
                Text(
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    ),
                    text = stringResource(id = R.string.go_to_chat_text)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutTabPreview() {
    ComposeDepartmentTheme {
        Surface {
            About(isDarkTheme = true)
        }
    }
}