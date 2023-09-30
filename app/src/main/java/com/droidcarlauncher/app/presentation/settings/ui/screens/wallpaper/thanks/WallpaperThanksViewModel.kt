/*
 * WallpaperThanksViewModel.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 28/9/23 13:12
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens.wallpaper.thanks

import androidx.lifecycle.ViewModel
import com.droidcarlauncher.app.domain.model.settings.wallpaper.Wallpaper
import com.droidcarlauncher.app.presentation.settings.ui.screens.wallpaper.helper.WallpaperDetailHelper
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
