package com.example.composedepartment.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.AppNavigation
import com.example.composedepartment.ui.department.DepartmentScreen
import com.example.composedepartment.ui.department.details.EmployeeDetails
import com.example.composedepartment.ui.utils.goToPhoneSystemApp

//todo animate navigations
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavHostController,
    onDarkThemeToggle: (Boolean) -> Unit = {}
) {
    val context = LocalContext.current
    NavHost(navController, startDestination = AppNavigation.SplashNavScreen.route) {
        composable(AppNavigation.SplashNavScreen.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(AppNavigation.MainNavScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(AppNavigation.MainNavScreen.route) {
            DepartmentScreen(
                employees = Employees.employees,
                projects = Projects.projects,
                onDarkThemeToggle = onDarkThemeToggle,
                onEmployeeClick = { employeeId ->
                    navController.navigate(
                        AppNavigation.EmployeeDetailsScreen.routeWithArguments(
                            employeeId
                        )
                    )
                }
            )
        }
        with(AppNavigation.EmployeeDetailsScreen) {
            composable(
                route = route,
                arguments = listOf(
                    navArgument(argument0) {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                backStackEntry.arguments?.let { bundle ->
                    EmployeeDetails(
                        employee = Employees.employees.first {
                            it.id == bundle.getString(argument0)!!
                        },
                        onBackClicked = { navController.popBackStack() },
                        onCallClicked = { goToPhoneSystemApp(context = context, phone = it) }
                    )
                }
            }
        }
    }
}