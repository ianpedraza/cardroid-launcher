/*
 * SystemAppsHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 19/9/23 13:58
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.helper.systemapps

import com.cardroidlauncher.app.domain.model.applications.AppModel

interface SystemAppsHelper {
    fun fetchSystemApps(): List<AppModel>
    fun isVoiceSearchAvailable(): Boolean
    fun isAppInstalled(app: AppModel): Boolean
}
