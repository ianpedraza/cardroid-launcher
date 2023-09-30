/*
 * OrientationSettingsViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 12:52
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.general.orientation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardroidlauncher.app.domain.model.settings.general.GeneralSettings
import com.cardroidlauncher.app.domain.model.settings.general.orientation.ScreenOrientation
import com.cardroidlauncher.app.domain.usecases.settings.GetGeneralSettingsUseCase
import com.cardroidlauncher.app.domain.usecases.settings.SaveGeneralSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrientationSettingsViewModel @Inject constructor(
    private val getGeneralSettingsUseCase: GetGeneralSettingsUseCase,
    private val saveGeneralSettingsUseCase: SaveGeneralSettingsUseCase,
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _selectedOrientation = MutableStateFlow<ScreenOrientation?>(null)
    val selectedOrientation = _selectedOrientation.asStateFlow()

    private var settings: GeneralSettings? = null

    init {
        fetchOrientationSettings()
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun setOrientation(orientation: ScreenOrientation) {
        viewModelScope.launch {
            settings?.let {
                val newSettings = it.copy(orientation = orientation)
                saveGeneralSettingsUseCase(newSettings)
            }
        }
    }

    private fun fetchOrientationSettings() {
        viewModelScope.launch {
            getGeneralSettingsUseCase().onEach { settings ->
                this@OrientationSettingsViewModel.settings = settings
                _selectedOrientation.value = settings.orientation
            }.launchIn(viewModelScope)
        }
    }
}
