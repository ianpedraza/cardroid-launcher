/*
 * WallpaperThanksViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 28/9/23 13:12
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.thanks

import androidx.lifecycle.ViewModel
import com.cardroidlauncher.app.domain.model.settings.wallpaper.Wallpaper
import com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper.helper.detial.WallpaperDetailHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WallpaperThanksViewModel @Inject constructor(
    private val wallpaperDetailHelper: WallpaperDetailHelper
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _wallpapers = MutableStateFlow<List<Wallpaper>>(emptyList())
    val wallpapers = _wallpapers.asStateFlow()

    private val _wallpaperDetailsUrl = MutableStateFlow<String?>(null)
    val wallpaperDetailsUrl = _wallpaperDetailsUrl.asStateFlow()

    init {
        fetchWallpapers()
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onLaunchedWallpaperDetails() {
        _wallpaperDetailsUrl.value = null
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun onLaunchWallpaperDetails(author: String) {
        _wallpaperDetailsUrl.value = wallpaperDetailHelper.createUrl(author)
    }

    private fun fetchWallpapers() {
        _wallpapers.value = Wallpaper.values.filter { it.title != null && it.author != null }
    }
}
