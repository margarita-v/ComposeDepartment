package com.example.composedepartment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.composedepartment.ui.MainScreen
import com.example.composedepartment.ui.base.theme.BlackBackground
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

val LocalDarkThemeEnabled = compositionLocalOf { false }

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()
            val systemUiController = rememberSystemUiController()
            if (isDarkTheme) {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = false
                )
                systemUiController.setNavigationBarColor(
                    color = BlackBackground,
                    darkIcons = false
                )
            } else {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = true
                )
                systemUiController.setNavigationBarColor(
                    color = Color.Transparent,
                    darkIcons = true
                )
            }
            ComposeDepartmentTheme(darkTheme = isDarkTheme) {
                CompositionLocalProvider(LocalDarkThemeEnabled provides isDarkTheme) {
                    MainScreen(
                        rememberAnimatedNavController(),
                        onDarkThemeToggle = { viewModel.toggleDarkTheme(it) }
                    )
                }
            }
        }
    }
}