package com.example.composedepartment.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedepartment.R
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.custom.MaterialThemeCustom
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    delay: Long = 1000L,
    onFinished: () -> Unit = {}
) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(MaterialThemeCustom.colors.splash)
            .systemBarsPadding()
    ) {
        val (surf, image, logo) = createRefs()
        Image(
            modifier = Modifier.constrainAs(surf) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top, margin = 96.dp)
            },
            painter = painterResource(R.drawable.ic_surf),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            contentDescription = null
        )
        Image(
            modifier = Modifier.constrainAs(image) {
                centerHorizontallyTo(parent)
                top.linkTo(surf.bottom, margin = (-16).dp)
            },
            painter = painterResource(
                id = R.drawable.ic_splash_image_dark.takeIf { darkTheme }
                    ?: R.drawable.ic_splash_image_light
            ),
            contentDescription = null
        )
        Image(
            modifier = Modifier.constrainAs(logo) {
                centerHorizontallyTo(parent)
                bottom.linkTo(parent.bottom, margin = 24.dp)
            },
            painter = painterResource(
                id = R.drawable.ic_android_dark.takeIf { darkTheme }
                    ?: R.drawable.ic_android_light
            ),
            contentDescription = null
        )
    }

    LaunchedEffect(key1 = Unit, block = {
        delay(delay)
        onFinished()
    })
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Composable
private fun SplashScreenPreview() {
    ComposeDepartmentTheme {
        SplashScreen(modifier = Modifier.fillMaxSize())
    }
}
