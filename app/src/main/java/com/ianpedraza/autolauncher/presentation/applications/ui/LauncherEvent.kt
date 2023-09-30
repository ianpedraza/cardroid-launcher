/*
 * LauncherEvent.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 18:42
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.ui

import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon

sealed class LauncherEvent {
    data class OnAppsDragged(val origin: AppModel, val target: AppModel) : LauncherEvent()
    data class OnLaunchApp(val app: AppModel) : LauncherEvent()
    object OnLaunchClockSettings : LauncherEvent()
    object OnLaunchSettings : LauncherEvent()
    object OnLaunchVoiceSearch : LauncherEvent()
    object OnResetStandby : LauncherEvent()
    data class OnUninstallApp(val app: AppModel) : LauncherEvent()
    data class OnEditApp(val app: AppModel) : LauncherEvent()
    data class OnHideApp(val app: AppModel) : LauncherEvent()
    data class OnLaunchAppInfo(val app: AppModel) : LauncherEvent()
    object OnDismissEditApp : LauncherEvent()
    data class OnChangeEditApp(val newApp: AppModel) : LauncherEvent()
    data class OnSaveEditedApp(val newApp: AppModel, val customIcon: CustomIcon?) : LauncherEvent()
}
