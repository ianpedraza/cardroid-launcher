/*
 * WallpaperDataHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 2/10/23 11:49
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.helper.wallpaper

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.cardroidlauncher.app.di.DefaultDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WallpaperDataHelper @Inject constructor(
    private val wallpaperManager: WallpaperManager,
    @ApplicationContext private val context: Context,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : WallpaperHelper {

    override suspend fun setWallpaper(id: Int): Unit = withContext(dispatcher) {
        BitmapFactory.decodeResource(context.resources, id)?.let { bitmap ->
            setWallpaper(bitmap)
        }
    }

    override suspend fun setWallpaper(bitmap: Bitmap): Unit =
        withContext(dispatcher) { wallpaperManager.setBitmap(bitmap) }
}
