/*
 * NavigationDataComponent.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 18:37
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.navigation

import androidx.navigation.NavController

class NavigationDataComponent(
    private val navController: NavController,
) : NavigationComponent {

    override fun goToSettings() {
        val route = Routes.SettingsScreen.route
        navController.navigate(route)
    }

    override fun goToGeneralSettings() {
        val route = Routes.SettingsGeneralScreen.route
        navController.navigate(route)
    }

    override fun goToAppearanceSettings() {
        val route = Routes.SettingsAppearanceScreen.route
        navController.navigate(route)
    }

    override fun goToApplicationsSettings() {
        val route = Routes.SettingsApplicationsScreen.route
        navController.navigate(route)
    }

    override fun goToStandbyModeSettings() {
        val route = Routes.SettingsStandbyModeScreen.route
        navController.navigate(route)
    }

    override fun goToWallpaperSettings() {
        val route = Routes.SettingsWallpaperScreen.route
        navController.navigate(route)
    }

    override fun goToWallpaperThanks() {
        val route = Routes.WallpaperThanksScreen.route
        navController.navigate(route)
    }

    override fun goToDarkThemeSettings() {
        val route = Routes.SettingsDarkThemeScreen.route
        navController.navigate(route)
    }

    override fun goToIconPackSettings() {
        val route = Routes.SettingsIconPackScreen.route
        navController.navigate(route)
    }

    override fun goToOrientationSettings() {
        val route = Routes.SettingsOrientationScreen.route
        navController.navigate(route)
    }

    override fun goToSteeringWheelPositionSettings() {
        val route = Routes.SettingsSteeringWheelPositionScreen.route
        navController.navigate(route)
    }

    override fun goToClockFormatSettings() {
        val route = Routes.SettingsClockFormatScreen.route
        navController.navigate(route)
    }

    override fun back() {
        navController.popBackStack()
    }
}
