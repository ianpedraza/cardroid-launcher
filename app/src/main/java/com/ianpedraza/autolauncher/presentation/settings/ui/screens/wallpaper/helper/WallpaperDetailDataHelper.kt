/*
 * WallpaperDetailDataHelper.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 28/9/23 15:06
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.settings.ui.screens.wallpaper.helper

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
