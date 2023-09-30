/*
 * LauncherViewModel.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 18:41
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui.screens

import androidx.lifecycle.viewModelScope
import com.ianpedraza.autolauncher.di.IoDispatcher
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.model.settings.general.GeneralSettings
import com.ianpedraza.autolauncher.domain.model.settings.wallpaper.Wallpaper
import com.ianpedraza.autolauncher.domain.usecases.applications.EditApplicationUseCase
import com.ianpedraza.autolauncher.domain.usecases.applications.GetVisibleApplicationsUseCase
import com.ianpedraza.autolauncher.domain.usecases.applications.HideApplicationsUseCase
import com.ianpedraza.autolauncher.domain.usecases.applications.MoveApplicationsUseCase
import com.ianpedraza.autolauncher.domain.usecases.applications.RemoveApplicationsUseCase
import com.ianpedraza.autolauncher.domain.usecases.applications.SaveSystemApplicationsUseCase
import com.ianpedraza.autolauncher.domain.usecases.settings.GetGeneralSettingsUseCase
import com.ianpedraza.autolauncher.domain.usecases.settings.GetStandbySettingsUseCase
import com.ianpedraza.autolauncher.domain.usecases.settings.GetWallpaperSettingsUseCase
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import com.ianpedraza.autolauncher.domain.utils.extension.AppModelExtension.indexOrNull
import com.ianpedraza.autolauncher.presentation.applications.helper.systemapps.SystemAppsHelper
import com.ianpedraza.autolauncher.presentation.applications.helper.validator.CustomAppsValidator
import com.ianpedraza.autolauncher.presentation.applications.ui.LauncherEvent
import com.ianpedraza.autolauncher.presentation.main.helper.standby.StandbyHelper
import com.ianpedraza.autolauncher.presentation.main.ui.StandbyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val systemAppsHelper: SystemAppsHelper,
    private val customAppsValidator: CustomAppsValidator,
    private val saveSystemApplicationsUseCase: SaveSystemApplicationsUseCase,
    private val getVisibleApplicationsUseCase: GetVisibleApplicationsUseCase,
    private val moveApplicationsUseCase: MoveApplicationsUseCase,
    private val getWallpaperSettingsUseCase: GetWallpaperSettingsUseCase,
    private val getGeneralSettingsUseCase: GetGeneralSettingsUseCase,
    private val removeApplicationsUseCase: RemoveApplicationsUseCase,
    private val hideApplicationsUseCase: HideApplicationsUseCase,
    private val editApplicationUseCase: EditApplicationUseCase,
    getStandbySettingsUseCase: GetStandbySettingsUseCase,
    standbyHelper: StandbyHelper,
) : StandbyViewModel(getStandbySettingsUseCase, standbyHelper) {

    private var _appsState = MutableStateFlow<DataState<List<AppModel>>>(DataState.Loading)
    val appsState: StateFlow<DataState<List<AppModel>>> = _appsState.asStateFlow()

    private val _appForLaunch = MutableStateFlow<AppModel?>(null)
    val appForLaunch = _appForLaunch.asStateFlow()

    private val _launchClockSettings = MutableStateFlow(false)
    val launchClockSettings = _launchClockSettings.asStateFlow()

    private val _launchSettings = MutableStateFlow(false)
    val launchSettings = _launchSettings.asStateFlow()

    private val _launchVoiceSearch = MutableStateFlow(false)
    val launchVoiceSearch = _launchVoiceSearch.asStateFlow()

    private val _isVoiceSearchAvailable = MutableStateFlow(false)
    val isVoiceSearchAvailable = _isVoiceSearchAvailable.asStateFlow()

    private val _wallpaper = MutableStateFlow<Wallpaper>(Wallpaper.default)
    val wallpaper = _wallpaper.asStateFlow()

    private val _generalSettings = MutableStateFlow<GeneralSettings?>(null)
    val generalSettings = _generalSettings.asStateFlow()

    private val _appToUninstall = MutableStateFlow<AppModel?>(null)
    val appToUninstall = _appToUninstall.asStateFlow()

    private val _appToEdit = MutableStateFlow<AppModel?>(null)
    val appToEdit = _appToEdit.asStateFlow()

    private val _editAppError = MutableStateFlow<Int?>(null)
    val editAppError = _editAppError.asStateFlow()

    private val _appToLaunchInfo = MutableStateFlow<AppModel?>(null)
    val appToLaunchInfo = _appToLaunchInfo.asStateFlow()

    private var movingAppsJob: Job? = null
    private var fetchAppsJob: Job? = null

    init {
        fetchApps()
        fetchWallpaper()
        fetchGeneralSettings()
        initVoiceSearch()
    }

    override fun onCleared() {
        movingAppsJob?.cancel()
        fetchAppsJob?.cancel()
        super.onCleared()
    }


    fun onEvent(event: LauncherEvent) {
        when (event) {
            is LauncherEvent.OnAppsDragged -> onDraggedApp(event.origin, event.target)
            is LauncherEvent.OnLaunchApp -> onLaunchApp(event.app)
            LauncherEvent.OnLaunchClockSettings -> onLaunchClockSettings()
            LauncherEvent.OnLaunchSettings -> onLaunchSettings()
            LauncherEvent.OnLaunchVoiceSearch -> onLaunchVoiceSearch()
            LauncherEvent.OnResetStandby -> resetStandbyTimer()
            is LauncherEvent.OnEditApp -> onEditApp(event.app)
            is LauncherEvent.OnHideApp -> onHideApp(event.app)
            is LauncherEvent.OnUninstallApp -> onUninstallApp(event.app)
            is LauncherEvent.OnChangeEditApp -> onEditAppChanged(event.newApp)
            LauncherEvent.OnDismissEditApp -> onAppEditAppDismiss()
            is LauncherEvent.OnSaveEditedApp -> onEditAppSave(event.newApp, event.customIcon)
            is LauncherEvent.OnLaunchAppInfo -> onLaunchAppInfo(event.app)
        }
    }

    fun fetchApps() {
        fetchAppsJob?.cancel()
        fetchAppsJob = viewModelScope.launch {
            getVisibleApplicationsUseCase().onEach {
                _appsState.value = it
                checkUninstalledApps()
            }.launchIn(viewModelScope)
        }
    }

    fun fetchSystemApps() {
        viewModelScope.launch {
            val systemApps = withContext(ioDispatcher) {
                systemAppsHelper.fetchSystemApps()
            }
            saveSystemApplicationsUseCase(systemApps)
        }
    }

    fun onLaunchedApplication() {
        _appForLaunch.value = null
    }

    fun onLaunchedClockSetting() {
        _launchClockSettings.value = false
    }

    fun onLaunchedSettings() {
        _launchSettings.value = false
    }

    fun onLaunchedVoiceSearch() {
        _launchVoiceSearch.value = false
    }

    fun onUninstalledApp() {
        viewModelScope.launch {
            _appToUninstall.value?.let { app ->
                if (systemAppsHelper.isAppInstalled(app).not()) {
                    removeApplicationsUseCase(listOf(app))
                }
            }
            _appToUninstall.value = null
        }
    }

    fun checkUninstalledApps() {
        viewModelScope.launch {
            val currentState = _appsState.value
            if (currentState is DataState.Success) {
                val uninstalledApps = currentState.data.filter { app ->
                    systemAppsHelper.isAppInstalled(app).not()
                }
                removeApplicationsUseCase(uninstalledApps)
            }
        }
    }

    fun onLaunchedAppInfo() {
        _appToLaunchInfo.value = null
    }

    private fun onAppEditAppDismiss() {
        _appToEdit.value = null
        _editAppError.value = null
    }

    private fun onEditAppChanged(newApp: AppModel) {
        _editAppError.value = customAppsValidator.validate(newApp)
    }

    private fun onLaunchAppInfo(app: AppModel) {
        _appToLaunchInfo.value = app
    }

    private fun onEditAppSave(newApp: AppModel, customIcon: CustomIcon?) {
        viewModelScope.launch {
            editApplicationUseCase(newApp, customIcon)
            _appToEdit.value = null
        }
    }

    private fun onLaunchApp(app: AppModel) {
        _appForLaunch.value = app
    }

    private fun onLaunchClockSettings() {
        _launchClockSettings.value = true
    }

    private fun onLaunchSettings() {
        _launchSettings.value = true
    }

    private fun onLaunchVoiceSearch() {
        _launchVoiceSearch.value = true
    }

    private fun onUninstallApp(app: AppModel) {
        _appToUninstall.value = app
    }

    private fun onEditApp(app: AppModel) {
        _editAppError.value = null
        _appToEdit.value = app
    }

    private fun onHideApp(app: AppModel) {
        viewModelScope.launch {
            hideApplicationsUseCase(listOf(app))
        }
    }

    private fun onDraggedApp(origin: AppModel, target: AppModel) {
        movingAppsJob?.cancel()
        if (origin.id != target.id) {
            movingAppsJob = viewModelScope.launch {
                val currentState = _appsState.value
                if (currentState is DataState.Success) {
                    val apps = currentState.data
                    val originIndex = apps.indexOrNull(origin)
                    val targetIndex = apps.indexOrNull(target)
                    if (originIndex != null && targetIndex != null) {
                        moveApplicationsUseCase(originIndex, targetIndex)
                    }
                }
            }
        }
    }

    private fun initVoiceSearch() {
        viewModelScope.launch {
            _isVoiceSearchAvailable.value = withContext(ioDispatcher) {
                systemAppsHelper.isVoiceSearchAvailable()
            }
        }
    }

    private fun fetchWallpaper() {
        viewModelScope.launch {
            getWallpaperSettingsUseCase().onEach {
                _wallpaper.value = it.wallpaper
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchGeneralSettings() {
        viewModelScope.launch {
            getGeneralSettingsUseCase().onEach {
                _generalSettings.value = it
            }.launchIn(viewModelScope)
        }
    }
}
