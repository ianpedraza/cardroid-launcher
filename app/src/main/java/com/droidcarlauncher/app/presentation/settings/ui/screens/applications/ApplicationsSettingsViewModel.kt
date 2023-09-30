/*
 * ApplicationsSettingsViewModel.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 18/9/23 19:25
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.settings.ui.screens.applications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.usecases.applications.GetAllApplicationsUseCase
import com.droidcarlauncher.app.domain.usecases.applications.HideApplicationsUseCase
import com.droidcarlauncher.app.domain.usecases.applications.UnhideApplicationsUseCase
import com.droidcarlauncher.app.domain.utils.datastate.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationsSettingsViewModel @Inject constructor(
    private val getAllApplicationsUseCase: GetAllApplicationsUseCase,
    private val hideApplicationsUseCase: HideApplicationsUseCase,
    private val unhideApplicationsUseCase: UnhideApplicationsUseCase
) : ViewModel() {

    private val _appsListState = MutableStateFlow<DataState<List<AppModel>>>(DataState.Loading)
    val appsListState = _appsListState.asStateFlow()

    private val _isAllHidden = MutableStateFlow(true)
    val isAllHidden = _isAllHidden.asStateFlow()

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    private var getApplicationsJob: Job? = null

    init {
        fetchApplications()
    }

    fun toggleHide(app: AppModel) {
        viewModelScope.launch {
            if (app.hidden) {
                unhideApplicationsUseCase(listOf(app))
            } else {
                hideApplicationsUseCase(listOf(app))
            }
            _isAllHidden.value = true
        }
    }

    fun toggleAll() {
        viewModelScope.launch {
            val currentState = _appsListState.value
            if (currentState is DataState.Success) {
                val apps = currentState.data
                _isAllHidden.value = if (_isAllHidden.value) {
                    unhideApplicationsUseCase(apps)
                    false
                } else {
                    hideApplicationsUseCase(apps)
                    true
                }
            }
        }
    }

    fun onBackPerformed() {
        _goBack.value = false
    }

    fun onBackPressed() {
        _goBack.value = true
    }

    fun fetchApplications() {
        getApplicationsJob?.cancel()
        getApplicationsJob = viewModelScope.launch {
            getAllApplicationsUseCase().onEach {
                _appsListState.value = it
            }.launchIn(viewModelScope)
        }
    }

}
