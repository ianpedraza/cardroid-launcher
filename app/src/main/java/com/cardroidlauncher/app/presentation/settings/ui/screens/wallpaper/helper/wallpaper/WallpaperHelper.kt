/*
 * WallpaperHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 2/10/23 11:48
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.helper.wallpaper

import android.graphics.Bitmap
import androidx.annotation.DrawableRes

interface WallpaperHelper {
    suspend fun setWallpaper(@DrawableRes id: Int)
    suspend fun setWallpaper(bitmap: Bitmap)
}
