/*
 * SystemAppsDataHelper.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 19/9/23 11:58
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.helper.systemapps

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.ResolveInfoFlags
import android.os.Build
import com.ianpedraza.autolauncher.domain.mapper.AppMappers.toAppModelList
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SystemAppsDataHelper @Inject constructor(
    @ApplicationContext private val context: Context,
) : SystemAppsHelper {

    override fun fetchSystemApps(): List<AppModel> {
        val packageManager = context.packageManager
        val flag = PackageManager.GET_META_DATA

        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val apps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.queryIntentActivities(intent, ResolveInfoFlags.of(flag.toLong()))
        } else {
            packageManager.queryIntentActivities(intent, flag)
        }.filter { it.activityInfo.packageName != context.packageName }

        return apps.toAppModelList(context.packageManager)
    }

    override fun isVoiceSearchAvailable(): Boolean {
        val packageManager = context.packageManager
        val flag = PackageManager.GET_META_DATA
        val intent = Intent(Intent.ACTION_VOICE_COMMAND)

        val apps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.queryIntentActivities(intent, ResolveInfoFlags.of(flag.toLong()))
        } else {
            packageManager.queryIntentActivities(intent, flag)
        }
        return apps.isNotEmpty()
    }

    override fun isAppInstalled(app: AppModel): Boolean {
        val packageManager = context.packageManager
        return try {
            packageManager.getApplicationInfo(app.packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}
