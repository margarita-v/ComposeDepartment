package com.example.composedepartment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.composedepartment.ui.MainScreen
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.google.accompanist.insets.ProvideWindowInsets
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
            // todo fix status & navbar colors switching
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