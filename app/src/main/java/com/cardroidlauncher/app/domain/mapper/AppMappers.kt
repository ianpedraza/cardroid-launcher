/*
 * AppMappers.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 6/9/23 18:01
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.mapper

import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.cardroidlauncher.app.data.framework.applications.room.AppEntity
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.utils.extension.ResolveInfoExtensions.system

object AppMappers {

    private const val UNDEFINED_ID = 0L

    fun ResolveInfo.toAppModel(packageManager: PackageManager) = AppModel(
        id = UNDEFINED_ID,
        label = loadLabel(packageManager).toString(),
        activityName = activityInfo.name,
        packageName = activityInfo.packageName,
        isSystemApp = system,
    )

    fun List<ResolveInfo>.toAppModelList(packageManager: PackageManager) =
        map { it.toAppModel(packageManager) }

    fun AppEntity.toAppModel() = AppModel(
        id = id,
        label = label,
        packageName = packageName,
        activityName = activityName,
        position = position,
        hidden = hidden,
        isSystemApp = isSystemApp,
        customLabel = customLabel,
        customIconName = customIconName,
    )

    fun List<AppEntity>.toAppModel() = map { it.toAppModel() }

    fun AppModel.toAppEntity() = AppEntity(
        id = id,
        label = label,
        packageName = packageName,
        activityName = activityName,
        position = position,
        hidden = hidden,
        isSystemApp = isSystemApp,
        customLabel = customLabel,
        customIconName = customIconName,
    )

    fun List<AppModel>.toAppEntity() = map { it.toAppEntity() }
}
