/*
 * AppearanceSettingsViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 22/9/23 12:08
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.appearance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardroidlauncher.app.domain.model.settings.appearance.AppearanceSettingsOptions
import com.cardroidlauncher.app.domain.usecases.applications.ResetIconsUseCase
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppearanceSettingsViewModel @Inject constructor(
    private val resetIconsUseCase: ResetIconsUseCase
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _launchDarkThemeSettings = MutableStateFlow(false)
    val launchDarkThemeSettings = _launchDarkThemeSettings.asStateFlow()

    private val _launchIconPackSettings = MutableStateFlow(false)
    val launchIconPackSettings = _launchIconPackSettings.asStateFlow()

    private val _showResetIconsDialog = MutableStateFlow(false)
    val showResetIconsDialog = _showResetIconsDialog.asStateFlow()

    private val _resetIconsState = MutableStateFlow<DataState<Unit>?>(null)
    val resetIconsState = _resetIconsState.asStateFlow()

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun onLaunchedDarkThemeSettings() {
        _launchDarkThemeSettings.value = false
    }

    fun onLaunchedIconPackSettings() {
        _launchIconPackSettings.value = false
    }

    fun onResetIconsDialogDismiss() {
        _showResetIconsDialog.value = false
    }

    fun onResetIconsStateDismiss() {
        _resetIconsState.value = null
    }

    fun onConfirmResetIcons() {
        viewModelScope.launch {
            _showResetIconsDialog.value = false
            resetIconsUseCase().onEach {
                _resetIconsState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun onSettingsOptionClicked(option: AppearanceSettingsOptions) {
        when (option) {
            AppearanceSettingsOptions.DarkTheme -> onLaunchDarkThemeSettings()
            AppearanceSettingsOptions.IconPack -> onLaunchIconPackSettings()
            AppearanceSettingsOptions.ResetIcons -> onResetIcons()
        }
    }

    private fun onLaunchDarkThemeSettings() {
        _launchDarkThemeSettings.value = true
    }

    private fun onLaunchIconPackSettings() {
        _launchIconPackSettings.value = true
    }

    private fun onResetIcons() {
        _showResetIconsDialog.value = true
    }
}
