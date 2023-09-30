/*
 * Utils.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 7/9/23 14:01
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.droidcarlauncher.app.data.framework.iconpacks.manager.iconpack.IconPackDataManager
import com.droidcarlauncher.app.data.framework.iconpacks.manager.storage.IconStorageDataManager
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.model.settings.general.orientation.ScreenOrientation

object Utils {

    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelable(key) as? T
    }


    fun Int.withDensity(density: Float): Dp {
        return (this / density).dp
    }

    fun Context.launch(app: AppModel) {
        Intent().apply {
            component = ComponentName(app.packageName, app.activityName)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }.let(::startActivity)
    }

    fun Context.launchUrl(url: String) {
        try {
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }.let(::startActivity)
        } catch (e: Exception) {
            // NO-OP
        }
    }

    fun <K, V> Map<K, V>.contains(key: K, value: V): Boolean {
        return containsKey(key) || containsValue(value)
    }

    fun Context.launchVoiceAssistant() {
        Intent(Intent.ACTION_VOICE_COMMAND).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }.let(applicationContext::startActivity)
    }

    fun Context.launchAppInfo(app: AppModel) {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:${app.packageName}")
        }.let(::startActivity)
    }

    fun Context.getStockIcon(app: AppModel): Drawable? {
        val iconPackIcon = IconPackDataManager.getIcon(this, app)
        return iconPackIcon ?: getApplicationIcon(app.packageName)
    }

    fun Context.getApplicationIcon(packageName: String): Drawable? {
        return try {
            packageManager.getApplicationIcon(packageName)
        } catch (e: Exception) {
            null
        }
    }

    fun Context.getCustomIcon(iconName: String): Drawable? {
        return IconStorageDataManager(applicationContext).getIcon(iconName)
    }

    fun ManagedActivityResultLauncher<Intent, ActivityResult>.uninstallApp(app: AppModel) {
        Intent(Intent.ACTION_DELETE).apply {
            data = Uri.parse("package:${app.packageName}")
        }.let(::launch)
    }

    fun getScreenOrientation(config: Configuration): ScreenOrientation {
        val width = config.screenWidthDp
        val height = config.screenHeightDp

        val orientation = if (width > height) {
            ScreenOrientation.Landscape
        } else {
            ScreenOrientation.Portrait
        }

        return orientation
    }

    @Composable
    fun ComposableLifecycle(
        lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit,
    ) {
        DisposableEffect(lifeCycleOwner) {
            val observer = LifecycleEventObserver { source, event ->
                onEvent(source, event)
            }
            lifeCycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifeCycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }
}
