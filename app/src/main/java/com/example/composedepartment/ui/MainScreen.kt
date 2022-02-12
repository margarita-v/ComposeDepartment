package com.example.composedepartment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(navController, startDestination = "Splash") {
        composable("Splash") {
            SplashScreen(onFinished = { navController.navigate("Main") })
        }
        composable("Main") {
            DepartmentScreen()
        }
    }
}