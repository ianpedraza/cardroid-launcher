/*
 * ApplicationsMocks.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 29/9/23 13:22
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.mocks

import com.droidcarlauncher.app.data.framework.applications.room.AppEntity
import com.droidcarlauncher.app.domain.model.applications.AppModel

object ApplicationsMocks {

    fun getApplicationModel() = AppModel(
        id = 100L,
        label = "label",
        packageName = "com.package.app100",
        activityName = "activityName",
        position = 0,
        hidden = false,
        isSystemApp = true,
        customLabel = null,
        customIconName = null,
    )

    fun getApplicationsEntityList() = listOf(
        AppEntity(
            id = 0L,
            label = "label",
            packageName = "com.package.app0",
            activityName = "activityName",
            position = 0,
            hidden = false,
            isSystemApp = true,
            customLabel = null,
            customIconName = null,
        ),
        AppEntity(
            id = 1L,
            label = "label",
            packageName = "com.package.app1",
            activityName = "activityName",
            position = 1,
            hidden = false,
            isSystemApp = true,
            customLabel = null,
            customIconName = null,
        ),
        AppEntity(
            id = 2L,
            label = "label",
            packageName = "com.package.app2",
            activityName = "activityName",
            position = 2,
            hidden = false,
            isSystemApp = true,
            customLabel = null,
            customIconName = null,
        ),
    )

    fun getApplicationsModelList() = listOf(
        AppModel(
            id = 0L,
            label = "label",
            packageName = "com.package.app0",
            activityName = "activityName",
            position = 0,
            hidden = false,
            isSystemApp = true,
            customLabel = null,
            customIconName = null,
        ),
        AppModel(
            id = 1L,
            label = "label",
            packageName = "com.package.app1",
            activityName = "activityName",
            position = 1,
            hidden = false,
            isSystemApp = true,
            customLabel = null,
            customIconName = null,
        ),
        AppModel(
            id = 2L,
            label = "label",
            packageName = "com.package.app2",
            activityName = "activityName",
            position = 2,
            hidden = false,
            isSystemApp = true,
            customLabel = null,
            customIconName = null,
        ),
    )
}
