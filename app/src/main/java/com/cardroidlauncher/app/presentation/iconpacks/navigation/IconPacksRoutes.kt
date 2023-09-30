/*
 * IconPacksRoutes.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 15:48
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.iconpacks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.presentation.iconpacks.ui.screens.iconpack.IconPackScreen
import com.cardroidlauncher.app.presentation.iconpacks.ui.screens.iconpacks.IconPacksScreen
import com.cardroidlauncher.app.presentation.main.ui.animations.ScreenTransition


sealed class IconPacksRoutes(val route: String) {

    object IconPacksScreen : IconPacksRoutes(ICON_PACKS_SCREEN) {

        fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: IconPacksNavigationComponent,
            app: AppModel?
        ): @Composable () -> Unit = {
            IconPacksScreen(
                app = app,
                navigationComponent = navigationComponent,
            )
        }

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: IconPacksNavigationComponent
        ): @Composable () -> Unit = createScreen(
            navBackStackEntry = navBackStackEntry,
            navigationComponent = navigationComponent,
            app = null
        )
    }

    object IconPackScreen : IconPacksRoutes("$ICON_PACK_SCREEN/{$PARAM_ICON_PACK_PACKAGE_NAME}") {

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(PARAM_ICON_PACK_PACKAGE_NAME) { type = NavType.StringType }
        )

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: IconPacksNavigationComponent
        ): @Composable () -> Unit = {
            IconPackScreen(
                navigationComponent = navigationComponent
            )
        }

        fun createRoute(iconPackJson: String): String {
            return "$ICON_PACK_SCREEN/$iconPackJson"
        }
    }

    open val arguments: List<NamedNavArgument> = emptyList()
    open val transition: ScreenTransition = ScreenTransition.Defaults.StartToEnd

    abstract fun createScreen(
        navBackStackEntry: NavBackStackEntry,
        navigationComponent: IconPacksNavigationComponent,
    ): @Composable () -> Unit

    companion object {
        private const val ICON_PACKS_SCREEN = "icon-packs-screen"
        private const val ICON_PACK_SCREEN = "icon-pack-screen"

        const val PARAM_ICON_PACK_PACKAGE_NAME = "param-icon-pack-package-name"

        val values get() = IconPacksRoutes::class.sealedSubclasses.mapNotNull { it.objectInstance }

        val first = IconPacksScreen.route
    }

}
