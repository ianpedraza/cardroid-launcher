/*
 * SettingsWallpaperViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 7/9/23 19:58
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.wallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardroidlauncher.app.domain.model.settings.wallpaper.Wallpaper
import com.cardroidlauncher.app.domain.model.settings.wallpaper.WallpaperSettings
import com.cardroidlauncher.app.domain.usecases.settings.GetWallpaperSettingsUseCase
import com.cardroidlauncher.app.domain.usecases.settings.SaveWallpaperSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpaperSettingsViewModel @Inject constructor(
    private val saveWallpaperSettingsUseCase: SaveWallpaperSettingsUseCase,
    private val getWallpaperSettingsUseCase: GetWallpaperSettingsUseCase,
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _selectedWallpaper = MutableStateFlow<Wallpaper>(Wallpaper.default)
    val selectedWallpaper = _selectedWallpaper.asStateFlow()

    private val _launchWallpapersThanks = MutableStateFlow(false)
    val launchWallpapersThanks = _launchWallpapersThanks.asStateFlow()

    private var settings: WallpaperSettings? = null

    init {
        fetchWallpaperSettings()
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onLaunchedWallpapersThanks() {
        _launchWallpapersThanks.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun setWallpaper(wallpaper: Wallpaper) {
        viewModelScope.launch {
            settings?.let {
                val newSettings = it.copy(wallpaper = wallpaper)
                saveWallpaperSettingsUseCase(newSettings)
            }
        }
    }

    fun onLaunchWallpapersThanks() {
        _launchWallpapersThanks.value = true
    }

    private fun fetchWallpaperSettings() {
        viewModelScope.launch {
            getWallpaperSettingsUseCase().onEach { settings ->
                this@WallpaperSettingsViewModel.settings = settings
                _selectedWallpaper.value = settings.wallpaper
            }.launchIn(viewModelScope)
        }
    }

}
