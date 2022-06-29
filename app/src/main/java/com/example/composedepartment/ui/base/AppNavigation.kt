package com.example.composedepartment.ui.base

import ru.surfstudio.compose.routing.NavigationRoute
import ru.surfstudio.compose.routing.NavigationRouteArgument1

// The small app is made on learning purpose, all routes are stored in a single object
object AppNavigation {
    val SplashNavScreen = object : NavigationRoute {
        override val route: String = "Splash"
    }
    val MainNavScreen = object : NavigationRoute {
        override val route: String = "Main"
    }
    val EmployeeDetailsNavScreen = object : NavigationRouteArgument1 {
        override val argument0: String = "employee_id"
        override val route: String = "EmployeeDetails/{$argument0}"
    }
    val ProjectDetailsNavScreen = object : NavigationRouteArgument1 {
        override val argument0: String = "project_id"
        override val route: String = "ProjectDetails/{$argument0}"
    }
    val SearchNavScreen = object : NavigationRoute {
        override val route: String = "Search"
    }
    val AnimationsNavScreen = object : NavigationRoute {
        override val route: String = "Animations"
    }
}