/*
 * SettingsStandbyModeViewModel.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 16:59
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.settings.ui.screens.standby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbyMode
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbySettings
import com.ianpedraza.autolauncher.domain.usecases.settings.GetStandbySettingsUseCase
import com.ianpedraza.autolauncher.domain.usecases.settings.SaveStandbySettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandbyModeSettingsViewModel @Inject constructor(
    private val getStandbySettingsUseCase: GetStandbySettingsUseCase,
    private val saveStandbySettingsUseCase: SaveStandbySettingsUseCase,
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _standbySettings = MutableStateFlow<StandbySettings?>(null)

    private val _standbyMode = MutableStateFlow<StandbyMode>(StandbyMode.default)
    val standbyMode = _standbyMode.asStateFlow()

    private val _standbyModeEnabled = MutableStateFlow(false)
    val standbyModeEnabled = _standbyModeEnabled.asStateFlow()

    init {
        fetchStandbyMode()
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun setStandbyMode(mode: StandbyMode) {
        viewModelScope.launch {
            _standbySettings.value?.let {
                val newSettings = it.copy(standbyMode = mode)
                saveStandbySettingsUseCase(newSettings)
            }
        }
    }

    fun toggleStandbyMode() {
        viewModelScope.launch {
            _standbySettings.value?.let {
                val newSettings = it.copy(enabled = it.enabled.not())
                saveStandbySettingsUseCase(newSettings)
            }
        }
    }

    private fun fetchStandbyMode() {
        viewModelScope.launch {
            getStandbySettingsUseCase().onEach {
                _standbySettings.value = it
                _standbyMode.value = it.standbyMode
                _standbyModeEnabled.value = it.enabled
            }.launchIn(viewModelScope)
        }
    }
}
