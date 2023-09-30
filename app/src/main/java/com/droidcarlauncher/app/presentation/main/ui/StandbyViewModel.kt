/*
 * StandbyViewModel.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 15:55
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcarlauncher.app.domain.usecases.settings.GetStandbySettingsUseCase
import com.droidcarlauncher.app.presentation.main.helper.standby.StandbyHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class StandbyViewModel @Inject constructor(
    private val getStandbySettingsUseCase: GetStandbySettingsUseCase,
    private val standbyHelper: StandbyHelper,
) : ViewModel() {

    private val _standbyModeEnabled = MutableStateFlow(false)
    val standbyModeEnabled = _standbyModeEnabled.asStateFlow()

    private var standbyTimerJob: Job? = null
    private var active = false
    private var enabled = false
    private var time: Long? = null

    init {
        fetchStandbySettings()
    }

    override fun onCleared() {
        super.onCleared()
        standbyTimerJob?.cancel()
    }

    fun resetStandbyTimer() {
        standbyTimerJob?.cancel()
        _standbyModeEnabled.value = false
        if (active && enabled && time != null) {
            standbyTimerJob = viewModelScope.launch {
                standbyHelper.waitStandby(time!!)
                _standbyModeEnabled.value = true
            }
        }
    }

    fun resumeStandbyTimer() {
        active = true
        resetStandbyTimer()
    }

    fun pauseStandbyTimer() {
        standbyTimerJob?.cancel()
        _standbyModeEnabled.value = false
        active = false
    }

    private fun fetchStandbySettings() {
        viewModelScope.launch {
            getStandbySettingsUseCase().onEach {
                enabled = it.enabled
                time = it.standbyMode.value

                if (enabled.not()) {
                    pauseStandbyTimer()
                } else if (active) {
                    resetStandbyTimer()
                }
            }.launchIn(viewModelScope)
        }
    }
}
