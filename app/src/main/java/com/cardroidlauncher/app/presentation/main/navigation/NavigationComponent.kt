/*
 * NavigationComponent.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 7/9/23 17:27
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.navigation

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
    fun goToIconSizeSettings()
    fun goToOrientationSettings()
    fun goToSteeringWheelPositionSettings()
    fun goToClockFormatSettings()
    fun back()
}
