/*
 * GeneralSettingsViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 12:35
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.general

import androidx.lifecycle.ViewModel
import com.cardroidlauncher.app.domain.model.settings.general.GeneralSettingsOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GeneralSettingsViewModel @Inject constructor() : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _launchOrientationSettings = MutableStateFlow(false)
    val launchOrientationSettings = _launchOrientationSettings.asStateFlow()

    private val _launchSteeringWheelPositionSettings = MutableStateFlow(false)
    val launchSteeringWheelPositionSettings = _launchSteeringWheelPositionSettings.asStateFlow()

    private val _launchClockFormatSettings = MutableStateFlow(false)
    val launchClockFormatSettings = _launchClockFormatSettings.asStateFlow()

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onLaunchedOrientationSettings() {
        _launchOrientationSettings.value = false
    }

    fun onLaunchedSteeringWheelPositionSettings() {
        _launchSteeringWheelPositionSettings.value = false
    }

    fun onLaunchedSClockFormatSettings() {
        _launchClockFormatSettings.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun onSettingOptionClicked(option: GeneralSettingsOptions) {
        when (option) {
            GeneralSettingsOptions.Orientation -> launchOrientationSettings()
            GeneralSettingsOptions.SteeringWheelPosition -> launchSteeringWheelPositionSettings()
            GeneralSettingsOptions.ClockFormat -> launchClockFormatSettings()
        }
    }

    private fun launchOrientationSettings() {
        _launchOrientationSettings.value = true
    }

    private fun launchSteeringWheelPositionSettings() {
        _launchSteeringWheelPositionSettings.value = true
    }

    private fun launchClockFormatSettings() {
        _launchClockFormatSettings.value = true
    }
}
