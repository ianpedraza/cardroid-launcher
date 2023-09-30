/*
 * IconPackChooserViewModel.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 22/9/23 12:49
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens.appearance.iconpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcarlauncher.app.domain.model.iconpacks.IconPackModel
import com.droidcarlauncher.app.domain.model.settings.appearance.AppearanceSettings
import com.droidcarlauncher.app.domain.usecases.iconpacks.FetchIconPacksUseCase
import com.droidcarlauncher.app.domain.usecases.settings.GetAppearanceSettingsUseCase
import com.droidcarlauncher.app.domain.usecases.settings.SaveAppearanceSettingsUseCase
import com.droidcarlauncher.app.domain.utils.datastate.DataState
import com.droidcarlauncher.app.presentation.settings.ui.screens.appearance.iconpack.helper.IconPackHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IconPackChooserViewModel @Inject constructor(
    private val getAppearanceSettingsUseCase: GetAppearanceSettingsUseCase,
    private val saveAppearanceSettingsUseCase: SaveAppearanceSettingsUseCase,
    private val fetchIconPacksUseCase: FetchIconPacksUseCase,
    private val iconPackHelper: IconPackHelper
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _launchPlayStore = MutableStateFlow(false)
    val launchPlayStore = _launchPlayStore.asStateFlow()

    private val _iconPacksState =
        MutableStateFlow<DataState<List<IconPackModel>>>(DataState.Loading)
    val iconPacksState = _iconPacksState.asStateFlow()

    private val _selectedIconPackPackageName = MutableStateFlow<String?>(null)
    val selectedIconPackPackageName = _selectedIconPackPackageName.asStateFlow()

    var settings: AppearanceSettings? = null

    init {
        fetchIconPacks()
        getAppearanceSettings()
    }

    private fun getAppearanceSettings() {
        viewModelScope.launch {
            getAppearanceSettingsUseCase().onEach {
                settings = it
                _selectedIconPackPackageName.value = it.iconPack
            }.launchIn(viewModelScope)
        }
    }

    fun fetchIconPacks() {
        viewModelScope.launch {
            fetchIconPacksUseCase().onEach {
                _iconPacksState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onLaunchedPlayStore() {
        _launchPlayStore.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun launchPlayStore() {
        _launchPlayStore.value = true
    }

    fun selectIconPack(iconPack: IconPackModel?) {
        viewModelScope.launch {
            settings?.let {
                val packageName = iconPack?.packageName
                val newSettings = it.copy(iconPack = packageName)
                saveAppearanceSettingsUseCase(newSettings)
                iconPackHelper.loadIconPack(packageName)
            }
        }
    }

}
