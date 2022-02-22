package com.example.composedepartment.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import com.example.composedepartment.interactor.Employees
import com.example.composedepartment.interactor.Projects
import com.example.composedepartment.ui.base.AppNavigation
import com.example.composedepartment.ui.department.DepartmentScreen
import com.example.composedepartment.ui.department.details.EmployeeDetailsScreen
import com.example.composedepartment.ui.department.details.ProjectDetailsScreen
import com.example.composedepartment.ui.department.search.SearchScreen
import com.example.composedepartment.ui.utils.goToPhoneSystemApp
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavHostController,
    onDarkThemeToggle: (Boolean) -> Unit = {}
) {
    val context = LocalContext.current
    val onProjectClicked: (String) -> Unit = { projectId: String ->
        navController.navigate(
            AppNavigation.ProjectDetailsNavScreen.routeWithArguments(
                projectId
            )
        )
    }
    AnimatedNavHost(navController, startDestination = AppNavigation.SplashNavScreen.route) {
        composableAnimation(AppNavigation.SplashNavScreen.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(AppNavigation.MainNavScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composableAnimation(AppNavigation.MainNavScreen.route) {
            DepartmentScreen(
                employees = Employees.employees,
                projects = Projects.projects,
                onDarkThemeToggle = onDarkThemeToggle,
                onSearchClick = {
                    navController.navigate(AppNavigation.SearchNavScreen.route)
                },
                onEmployeeClick = { employeeId ->
                    navController.navigate(
                        AppNavigation.EmployeeDetailsNavScreen.routeWithArguments(
                            employeeId
                        )
                    )
                },
                onProjectClicked = onProjectClicked
            )
        }
        with(AppNavigation.EmployeeDetailsNavScreen) {
            composableAnimation(
                route = route,
                arguments = listOf(
                    navArgument(argument0) {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                backStackEntry.arguments?.let { bundle ->
                    EmployeeDetailsScreen(
                        employee = Employees.employees.first {
                            it.id == bundle.getString(argument0)!!
                        },
                        onBackClicked = { navController.popBackStack() },
                        onCallClicked = { goToPhoneSystemApp(context = context, phone = it) },
                        onProjectClicked = onProjectClicked
                    )
                }
            }
        }
        with(AppNavigation.ProjectDetailsNavScreen) {
            composableAnimation(
                route = route,
                arguments = listOf(
                    navArgument(argument0) {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                backStackEntry.arguments?.let { bundle ->
                    ProjectDetailsScreen(
                        project = Projects.projects.first {
                            it.id == bundle.getString(argument0)!!
                        },
                        onBackClicked = { navController.popBackStack() }
                    )
                }
            }
        }
        composableAnimation(AppNavigation.SearchNavScreen.route) {
            SearchScreen(onProjectClick = onProjectClicked)
        }
    }
}

private const val NAVIGATION_ANIMATION_DURATION_MS = 500

@ExperimentalAnimationApi
private fun NavGraphBuilder.composableAnimation(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    enterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition? = {
        slideIntoContainer(
            AnimatedContentScope.SlideDirection.Left,
            animationSpec = tween(NAVIGATION_ANIMATION_DURATION_MS)
        )
    },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentScope.SlideDirection.Left,
            animationSpec = tween(NAVIGATION_ANIMATION_DURATION_MS)
        )
    },
    popEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition? = {
        slideIntoContainer(
            AnimatedContentScope.SlideDirection.Right,
            animationSpec = tween(NAVIGATION_ANIMATION_DURATION_MS)
        )
    },
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentScope.SlideDirection.Right,
            animationSpec = tween(NAVIGATION_ANIMATION_DURATION_MS)
        )
    },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) { navBackStackEntry ->
        content.invoke(this, navBackStackEntry)
    }
}