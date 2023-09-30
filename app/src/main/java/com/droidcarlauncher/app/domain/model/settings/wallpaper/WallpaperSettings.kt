/*
 * WallpaperSettings.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 11:45
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.wallpaper

data class WallpaperSettings(
    val wallpaper: Wallpaper = Wallpaper.default,
)
