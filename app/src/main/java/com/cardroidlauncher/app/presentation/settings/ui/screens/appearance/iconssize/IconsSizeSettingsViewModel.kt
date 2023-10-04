/*
 * IconsSizeSettingsViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 4/10/23 11:09
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.iconssize

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardroidlauncher.app.domain.model.settings.appearance.AppearanceSettings
import com.cardroidlauncher.app.domain.model.settings.appearance.darkheme.DarkThemeMode
import com.cardroidlauncher.app.domain.model.settings.appearance.iconssize.IconsSize
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
class IconsSizeSettingsViewModel @Inject constructor(
    private val getAppearanceSettingsUseCase: GetAppearanceSettingsUseCase,
    private val saveAppearanceSettingsUseCase: SaveAppearanceSettingsUseCase,
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _iconsSize = MutableStateFlow<IconsSize?>(null)
    val iconsSize = _iconsSize.asStateFlow()

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

    fun setIconsSize(size: IconsSize) {
        viewModelScope.launch {
            settings?.let {
                val newSettings = it.copy(iconsSize = size)
                saveAppearanceSettingsUseCase(newSettings)
            }
        }
    }

    private fun fetchAppearanceSettings() {
        viewModelScope.launch {
            getAppearanceSettingsUseCase().onEach { settings ->
                this@IconsSizeSettingsViewModel.settings = settings
                _iconsSize.value = settings.iconsSize
            }.launchIn(viewModelScope)
        }
    }
}
