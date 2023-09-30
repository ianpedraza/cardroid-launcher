/*
 * AppNavigationGraph.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 18:37
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    routes: List<Routes> = Routes.values,
) {
    val navigationComponent = NavigationDataComponent(navController)

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.first,
    ) {
        routes.forEach { screenRoute ->
            with(screenRoute) {
                composable(
                    route = route,
                    arguments = arguments,
                    enterTransition = transition.enter,
                    exitTransition = transition.exit,
                    popEnterTransition = transition.popEnter,
                    popExitTransition = transition.popExit,
                ) { backStackEntry ->
                    createScreen(backStackEntry, navigationComponent).invoke()
                }
            }
        }
    }
}
