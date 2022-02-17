package com.example.composedepartment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.composedepartment.ui.MainScreen
import com.example.composedepartment.ui.base.theme.BlackBackground
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

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
                ProvideWindowInsets {
                    MainScreen(
                        rememberNavController(),
                        isDarkTheme = isDarkTheme, // todo use LocalProvider?
                        onDarkThemeToggle = { viewModel.toggleDarkTheme(it) }
                    )
                }
            }
        }
    }
}