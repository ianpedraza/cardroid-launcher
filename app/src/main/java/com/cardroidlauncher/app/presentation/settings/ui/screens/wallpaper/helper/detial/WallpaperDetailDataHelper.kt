/*
 * WallpaperDetailDataHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 2/10/23 11:48
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.helper.detial

import javax.inject.Inject

class WallpaperDetailDataHelper @Inject constructor() : WallpaperDetailHelper {

    override fun createUrl(author: String): String {
        return WALLPAPER_DETAIL_BASE_URL.replace(PARAM_AUTHOR, author)
    }

    companion object {
        private const val PARAM_AUTHOR = "{author}"
        const val WALLPAPER_DETAIL_BASE_URL = "https://dynamicwallpaper.club/u/$PARAM_AUTHOR"
    }
}
