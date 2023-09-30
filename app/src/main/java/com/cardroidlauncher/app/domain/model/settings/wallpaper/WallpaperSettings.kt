/*
 * WallpaperSettings.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 11:45
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.wallpaper

data class WallpaperSettings(
    val wallpaper: Wallpaper = Wallpaper.default,
)
