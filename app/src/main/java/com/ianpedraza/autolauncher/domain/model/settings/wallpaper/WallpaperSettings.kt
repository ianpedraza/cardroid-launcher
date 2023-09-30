/*
 * WallpaperSettings.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 11:45
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.model.settings.wallpaper

data class WallpaperSettings(
    val wallpaper: Wallpaper = Wallpaper.default,
)
