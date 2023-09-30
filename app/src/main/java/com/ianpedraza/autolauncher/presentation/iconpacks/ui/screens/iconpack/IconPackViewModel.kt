/*
 * IconPackViewModel.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 17:43
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.ui.screens.iconpack

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ianpedraza.autolauncher.di.SimpleGson
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel
import com.ianpedraza.autolauncher.domain.usecases.iconpacks.GetAllIconsUseCase
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import com.ianpedraza.autolauncher.presentation.iconpacks.navigation.IconPacksRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IconPackViewModel @Inject constructor(
    private val getAllIconsUseCase: GetAllIconsUseCase,
    @SimpleGson private val gson: Gson,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val iconPackJson: String =
        checkNotNull(savedStateHandle[IconPacksRoutes.PARAM_ICON_PACK_PACKAGE_NAME])

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _iconPack = MutableStateFlow<IconPackModel?>(null)
    val iconPack = _iconPack.asStateFlow()

    private val _iconsState = MutableStateFlow<DataState<List<CustomIcon>>>(DataState.Loading)
    val iconsState = _iconsState.asStateFlow()

    private val _iconSelected = MutableStateFlow<CustomIcon?>(null)
    val iconSelected = _iconSelected.asStateFlow()

    init {
        getIconPack(iconPackJson)
    }

    fun getAllIcons() {
        val data = iconPack.value ?: return
        viewModelScope.launch {
            getAllIconsUseCase(data).onEach {
                _iconsState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onIconSelectedPerformed() {
        _iconSelected.value = null
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun onIconSelected(customIcon: CustomIcon) {
        _iconSelected.value = customIcon
    }

    private fun getIconPack(json: String) {
        _iconPack.value = gson.fromJson(json, IconPackModel::class.java)
        getAllIcons()
    }
}
