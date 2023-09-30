/*
 * MainViewModel.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 12:11
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ianpedraza.autolauncher.domain.model.settings.appearance.darkheme.DarkThemeMode
import com.ianpedraza.autolauncher.domain.usecases.settings.GetAppearanceSettingsUseCase
import com.ianpedraza.autolauncher.presentation.settings.ui.screens.appearance.iconpack.helper.IconPackHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppearanceSettingsUseCase: GetAppearanceSettingsUseCase,
    private val iconPackHelper: IconPackHelper
) : ViewModel() {

    private val _darkThemeMode = MutableStateFlow<DarkThemeMode>(DarkThemeMode.default)
    val appearanceMode = _darkThemeMode.asStateFlow()

    init {
        fetchAppearanceMode()
    }

    private fun fetchAppearanceMode() {
        viewModelScope.launch {
            getAppearanceSettingsUseCase().onEach {
                _darkThemeMode.value = it.darkThemeMode
                iconPackHelper.loadIconPack(it.iconPack)
            }.launchIn(viewModelScope)
        }
    }
}
