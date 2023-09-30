/*
 * IconPacksViewModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 25/9/23 14:17
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.iconpacks.ui.screens.iconpacks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.model.iconpacks.IconPackModel
import com.cardroidlauncher.app.domain.usecases.iconpacks.FetchIconPacksUseCase
import com.cardroidlauncher.app.domain.usecases.iconpacks.GetSuggestedIconsUseCase
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import com.cardroidlauncher.app.presentation.main.helper.playstore.PlayStoreSearchHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IconPacksViewModel @Inject constructor(
    private val fetchIconPacksUseCase: FetchIconPacksUseCase,
    private val getSuggestedIconsUseCase: GetSuggestedIconsUseCase,
    private val playStoreSearchHelper: PlayStoreSearchHelper
) : ViewModel() {

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private val _iconPacksState =
        MutableStateFlow<DataState<List<IconPackModel>>>(DataState.Loading)
    val iconPacksState = _iconPacksState.asStateFlow()

    private val _suggestedIconsState =
        MutableStateFlow<DataState<List<CustomIcon>>>(DataState.Loading)
    val suggestedIconsState = _suggestedIconsState.asStateFlow()

    private val _launchPlayStoreUrl = MutableStateFlow<String?>(null)
    val launchPlayStoreUrl = _launchPlayStoreUrl.asStateFlow()

    private val _iconPackToLaunch = MutableStateFlow<IconPackModel?>(null)
    val iconPackToLaunch = _iconPackToLaunch.asStateFlow()

    private val _iconSelected = MutableStateFlow<CustomIcon?>(null)
    val iconSelected = _iconSelected.asStateFlow()

    private var currentApp: AppModel? = null
    private var getSuggestedIconsAttemptsCount = 0

    private val _canRetryGetSuggestions = MutableStateFlow(true)
    val canRetryGetSuggestions = _canRetryGetSuggestions.asStateFlow()

    init {
        getIconPacks()
    }

    fun setCurrentApp(app: AppModel?) {
        currentApp = app
        getSuggestedIcons()
    }

    fun onLaunchedPlayStore() {
        _launchPlayStoreUrl.value = null
    }

    fun onLaunchedIconPack() {
        _iconPackToLaunch.value = null
    }

    fun onIconSelectedPerformed() {
        _iconSelected.value = null
    }

    fun getIconPacks() {
        viewModelScope.launch {
            fetchIconPacksUseCase().onEach {
                _iconPacksState.value = it
            }.launchIn(viewModelScope)
        }
    }

    private fun getSuggestedIcons() {
        currentApp?.let {
            viewModelScope.launch {
                getSuggestedIconsUseCase(it).onEach {
                    _suggestedIconsState.value = it
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun launchPlayStore(query: String) {
        _launchPlayStoreUrl.value = playStoreSearchHelper.createUrl(query)
    }

    fun onIconSelected(customIcon: CustomIcon) {
        _iconSelected.value = customIcon
    }

    fun launchIconPack(iconPack: IconPackModel) {
        _iconPackToLaunch.value = iconPack
    }

    fun retryGetSuggestions() {
        if (getSuggestedIconsAttemptsCount < MAX_GET_SUGGESTIONS_ATTEMPTS) {
            getSuggestedIcons()
            getSuggestedIconsAttemptsCount++

            if (getSuggestedIconsAttemptsCount == MAX_GET_SUGGESTIONS_ATTEMPTS) {
                _canRetryGetSuggestions.value = false
            }
        }
    }

    companion object {
        private const val MAX_GET_SUGGESTIONS_ATTEMPTS = 3
    }
}
