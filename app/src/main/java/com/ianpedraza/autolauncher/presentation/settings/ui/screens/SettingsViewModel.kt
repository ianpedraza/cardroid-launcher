/*
 * SettingsViewModel.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 18:41
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.settings.ui.screens

import androidx.lifecycle.ViewModel
import com.ianpedraza.autolauncher.domain.model.settings.SettingsOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _launchGeneralSettings = MutableStateFlow(false)
    val launchGeneralSettings = _launchGeneralSettings.asStateFlow()

    private val _launchDarkModeSettings = MutableStateFlow(false)
    val launchDarkModeSettings = _launchDarkModeSettings.asStateFlow()

    private val _launchStandbyModeSettings = MutableStateFlow(false)
    val launchStandbyModeSettings = _launchStandbyModeSettings.asStateFlow()

    private val _launchWallpaperSettings = MutableStateFlow(false)
    val launchWallpaperSettings = _launchWallpaperSettings.asStateFlow()

    private val _launchApplicationsSettings = MutableStateFlow(false)
    val launchApplicationsSettings = _launchApplicationsSettings.asStateFlow()

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onLaunchedGeneralSettings() {
        _launchGeneralSettings.value = false
    }

    fun onLaunchedDarkModeSettings() {
        _launchDarkModeSettings.value = false
    }

    fun onLaunchedStandbyModeSettings() {
        _launchStandbyModeSettings.value = false
    }

    fun onLaunchedWallpaperSettings() {
        _launchWallpaperSettings.value = false
    }

    fun onLaunchedApplicationsSettings() {
        _launchApplicationsSettings.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun onSettingOptionClicked(option: SettingsOption) {
        when (option) {
            SettingsOption.General -> launchGeneralSettings()
            SettingsOption.Appearance -> launchDarkModeSettings()
            SettingsOption.StandbyMode -> launchStandbySettings()
            SettingsOption.Wallpaper -> launchWallpaperSetting()
            SettingsOption.Applications -> launchApplicationsSettings()
        }
    }

    private fun launchGeneralSettings() {
        _launchGeneralSettings.value = true
    }

    private fun launchDarkModeSettings() {
        _launchDarkModeSettings.value = true
    }

    private fun launchStandbySettings() {
        _launchStandbyModeSettings.value = true
    }

    private fun launchWallpaperSetting() {
        _launchWallpaperSettings.value = true
    }

    private fun launchApplicationsSettings() {
        _launchApplicationsSettings.value = true
    }
}
