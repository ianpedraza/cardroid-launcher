/*
 * ClockFormatSettingsViewModel.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 16/9/23 12:52
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens.general.clock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcarlauncher.app.domain.model.settings.clockformat.ClockFormat
import com.droidcarlauncher.app.domain.model.settings.general.GeneralSettings
import com.droidcarlauncher.app.domain.usecases.settings.GetGeneralSettingsUseCase
import com.droidcarlauncher.app.domain.usecases.settings.SaveGeneralSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClockFormatSettingsViewModel @Inject constructor(
    private val getGeneralSettingsUseCase: GetGeneralSettingsUseCase,
    private val saveGeneralSettingsUseCase: SaveGeneralSettingsUseCase,
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _selectedFormat = MutableStateFlow<ClockFormat?>(null)
    val selectedFormat = _selectedFormat.asStateFlow()

    private var settings: GeneralSettings? = null

    init {
        fetchClockFormatSettings()
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun setClockFormat(format: ClockFormat) {
        viewModelScope.launch {
            settings?.let {
                val newSettings = it.copy(clockFormat = format)
                saveGeneralSettingsUseCase(newSettings)
            }
        }
    }

    private fun fetchClockFormatSettings() {
        viewModelScope.launch {
            getGeneralSettingsUseCase().onEach { settings ->
                this@ClockFormatSettingsViewModel.settings = settings
                _selectedFormat.value = settings.clockFormat
            }.launchIn(viewModelScope)
        }
    }
}
