/*
 * Routes.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 18:37
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.cardroidlauncher.app.presentation.applications.ui.screens.LauncherScreen
import com.cardroidlauncher.app.presentation.main.ui.animations.ScreenTransition
import com.cardroidlauncher.app.presentation.settings.ui.screens.SettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.AppearanceSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.darktheme.DarkThemeSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.iconpack.IconPackChooserScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.iconssize.IconsSizeSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.applications.ApplicationsSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.general.GeneralSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.general.clock.ClockFormatSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.general.orientation.OrientationSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.general.steeringwheelposition.SteeringWheelPositionSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.standby.StandbyModeSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.WallpaperSettingsScreen
import com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.thanks.WallpaperThanksScreen

sealed class Routes(val route: String) {

    object HomeScreen : Routes(HOME_SCREEN) {

        override val transition = ScreenTransition.Defaults.None

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            LauncherScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsScreen : Routes(SETTINGS_SCREEN) {

        override val transition = object : ScreenTransition() {
            override val enter = ScreenTransition.Defaults.UpDown.enter
            override val exit = ScreenTransition.Defaults.StartToEnd.exit
            override val popEnter = ScreenTransition.Defaults.StartToEnd.popEnter
            override val popExit = ScreenTransition.Defaults.UpDown.popExit
        }

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            SettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsAppearanceScreen : Routes(SETTINGS_APPEARANCE_SCREEN) {

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            AppearanceSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsApplicationsScreen : Routes(SETTINGS_APPLICATIONS_SCREEN) {

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            ApplicationsSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsStandbyModeScreen : Routes(SETTINGS_STANDBY_MODE_SCREEN) {

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            StandbyModeSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsWallpaperScreen : Routes(SETTINGS_WALLPAPER_SCREEN) {

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            WallpaperSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object WallpaperThanksScreen : Routes(SETTINGS_WALLPAPER_THANKS) {

        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent
        ): @Composable () -> Unit = {
            WallpaperThanksScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsGeneralScreen : Routes(SETTINGS_GENERAL_SCREEN) {
        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            GeneralSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsOrientationScreen : Routes(SETTINGS_GENERAL_ORIENTATION_SCREEN) {
        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            OrientationSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsSteeringWheelPositionScreen : Routes(
        SETTINGS_GENERAL_STEERING_WHEEL_POSITION_SCREEN,
    ) {
        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            SteeringWheelPositionSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsClockFormatScreen : Routes(SETTINGS_GENERAL_CLOCK_FORMAT_SCREEN) {
        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            ClockFormatSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsDarkThemeScreen : Routes(SETTINGS_APPEARANCE_DARK_THEME) {
        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            DarkThemeSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsIconsSizeScreen : Routes(SETTINGS_APPEARANCE_ICONS_SIZE) {
        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            IconsSizeSettingsScreen(navigationComponent = navigationComponent)
        }
    }

    object SettingsIconPackScreen : Routes(SETTINGS_APPEARANCE_ICON_PACK) {
        override fun createScreen(
            navBackStackEntry: NavBackStackEntry,
            navigationComponent: NavigationComponent,
        ): @Composable () -> Unit = {
            IconPackChooserScreen(navigationComponent = navigationComponent)
        }
    }

    open val arguments: List<NamedNavArgument> = emptyList()
    open val transition: ScreenTransition = ScreenTransition.Defaults.StartToEnd

    abstract fun createScreen(
        navBackStackEntry: NavBackStackEntry,
        navigationComponent: NavigationComponent,
    ): @Composable () -> Unit

    companion object {
        private const val HOME_SCREEN = "home-screen"
        private const val SETTINGS_SCREEN = "settings-screen"
        private const val SETTINGS_APPEARANCE_SCREEN = "settings-appearance-screen"
        private const val SETTINGS_APPLICATIONS_SCREEN = "settings-applications-screen"
        private const val SETTINGS_STANDBY_MODE_SCREEN = "settings-standby-mode-screen"
        private const val SETTINGS_WALLPAPER_SCREEN = "settings-wallpaper-screen"
        private const val SETTINGS_WALLPAPER_THANKS = "settings-wallpaper-thanks-screen"
        private const val SETTINGS_GENERAL_SCREEN = "settings-general-screen"
        private const val SETTINGS_GENERAL_ORIENTATION_SCREEN =
            "settings-general-orientation-screen"
        private const val SETTINGS_GENERAL_STEERING_WHEEL_POSITION_SCREEN =
            "settings-general-steering-wheel-position-screen"
        private const val SETTINGS_GENERAL_CLOCK_FORMAT_SCREEN =
            "settings-general-clock-format-screen"
        private const val SETTINGS_APPEARANCE_DARK_THEME = "settings-appearance-dark-theme-screen"
        private const val SETTINGS_APPEARANCE_ICONS_SIZE = "settings-appearance-icons-size-screen"
        private const val SETTINGS_APPEARANCE_ICON_PACK = "settings-appearance-icon-pack-screen"

        val values get() = Routes::class.sealedSubclasses.mapNotNull { it.objectInstance }

        const val first = HOME_SCREEN
    }
}
