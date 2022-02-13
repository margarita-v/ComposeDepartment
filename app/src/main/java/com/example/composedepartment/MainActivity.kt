package com.example.composedepartment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.composedepartment.ui.MainScreen
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeDepartmentTheme {
                ProvideWindowInsets {
                    MainScreen(rememberNavController())
                }
            }
        }
    }
}