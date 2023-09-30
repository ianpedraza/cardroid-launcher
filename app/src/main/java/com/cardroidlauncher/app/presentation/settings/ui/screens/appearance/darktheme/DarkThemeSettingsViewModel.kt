/*
 * DarkThemeSettingsViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 22/9/23 11:57
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.darktheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardroidlauncher.app.domain.model.settings.appearance.AppearanceSettings
import com.cardroidlauncher.app.domain.model.settings.appearance.darkheme.DarkThemeMode
import com.cardroidlauncher.app.domain.usecases.settings.GetAppearanceSettingsUseCase
import com.cardroidlauncher.app.domain.usecases.settings.SaveAppearanceSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DarkThemeSettingsViewModel @Inject constructor(
    private val getAppearanceSettingsUseCase: GetAppearanceSettingsUseCase,
    private val saveAppearanceSettingsUseCase: SaveAppearanceSettingsUseCase,
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _darkThemeMode = MutableStateFlow<DarkThemeMode?>(null)
    val darkThemeMode = _darkThemeMode.asStateFlow()

    private var settings: AppearanceSettings? = null

    init {
        fetchAppearanceSettings()
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun setAppearanceMode(mode: DarkThemeMode) {
        viewModelScope.launch {
            settings?.let {
                val newSettings = it.copy(darkThemeMode = mode)
                saveAppearanceSettingsUseCase(newSettings)
            }
        }
    }

    private fun fetchAppearanceSettings() {
        viewModelScope.launch {
            getAppearanceSettingsUseCase().onEach { settings ->
                this@DarkThemeSettingsViewModel.settings = settings
                _darkThemeMode.value = settings.darkThemeMode
            }.launchIn(viewModelScope)
        }
    }
}
