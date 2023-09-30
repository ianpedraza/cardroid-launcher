/*
 * NavigationComponent.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 7/9/23 17:27
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.navigation

interface NavigationComponent {
    fun goToSettings()
    fun goToGeneralSettings()
    fun goToAppearanceSettings()
    fun goToApplicationsSettings()
    fun goToStandbyModeSettings()
    fun goToWallpaperSettings()
    fun goToWallpaperThanks()
    fun goToDarkThemeSettings()
    fun goToIconPackSettings()
    fun goToOrientationSettings()
    fun goToSteeringWheelPositionSettings()
    fun goToClockFormatSettings()
    fun back()
}
