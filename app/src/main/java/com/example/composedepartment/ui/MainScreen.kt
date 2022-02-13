package com.example.composedepartment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.ui.base.ScreenRoute
import com.example.composedepartment.ui.department.DepartmentScreen

@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(navController, startDestination = ScreenRoute.Splash.route) {
        composable(ScreenRoute.Splash.route) {
            SplashScreen(onFinished = { navController.navigate(ScreenRoute.Main.route) })
        }
        composable(ScreenRoute.Main.route) {
            DepartmentScreen(employees = Employees.employees)
        }
    }
}