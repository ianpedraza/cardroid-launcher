/*
 * SystemAppsHelper.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 19/9/23 13:58
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.helper.systemapps

import com.ianpedraza.autolauncher.domain.model.applications.AppModel

interface SystemAppsHelper {
    fun fetchSystemApps(): List<AppModel>
    fun isVoiceSearchAvailable(): Boolean
    fun isAppInstalled(app: AppModel): Boolean
}
