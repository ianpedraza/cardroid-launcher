/*
 * IconPacksNavigationGraph.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 15:46
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.ianpedraza.autolauncher.domain.model.applications.AppModel

@Composable
fun IconPacksNavigationGraph(
    modifier: Modifier = Modifier,
    app: AppModel? = null,
    navController: NavHostController,
    routes: List<IconPacksRoutes> = IconPacksRoutes.values
) {
    val navigationComponent = IconPacksNavigationDataComponent(navController, Gson())

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IconPacksRoutes.first,
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
                    when (screenRoute) {
                        is IconPacksRoutes.IconPacksScreen -> {
                            screenRoute.createScreen(
                                navBackStackEntry = backStackEntry,
                                navigationComponent = navigationComponent,
                                app = app,
                            ).invoke()
                        }

                        else -> {
                            createScreen(
                                navBackStackEntry = backStackEntry,
                                navigationComponent = navigationComponent
                            ).invoke()
                        }
                    }
                }
            }
        }
    }
}
