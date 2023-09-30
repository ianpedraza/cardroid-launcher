/*
 * SystemIconsPackDataSource.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 11:19
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.iconpacks

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import com.ianpedraza.autolauncher.data.datasource.iconpacks.IconPacksDataSource
import com.ianpedraza.autolauncher.data.framework.iconpacks.manager.cache.CustomIconsDataCache
import com.ianpedraza.autolauncher.di.IoDispatcher
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.getApplicationIcon
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class SystemIconPacksDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : IconPacksDataSource {

    private val iconPacks: MutableMap<String, IconPack> = mutableMapOf()

    override suspend fun fetchIconPacks(force: Boolean): List<IconPackModel> =
        withContext(dispatcher) {
            if (iconPacks.isEmpty() || force) {
                iconPacks.clear()

                val packageManager = context.packageManager
                val flag = PackageManager.GET_META_DATA

                val intents = listOf(
                    Intent("org.adw.launcher.THEMES"),
                    Intent("com.gau.go.launcherex.theme")
                )

                val apps: List<ResolveInfo> =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intents.flatMap { intent ->
                            packageManager.queryIntentActivities(
                                intent,
                                PackageManager.ResolveInfoFlags.of(flag.toLong())
                            )
                        }
                    } else {
                        intents.flatMap { intent ->
                            packageManager.queryIntentActivities(intent, flag)
                        }
                    }

                apps.forEach { resolveInfo ->
                    resolveInfo.toIconPack(packageManager).also { iconPack ->
                        iconPacks[iconPack.packageName] = iconPack
                    }
                }
            }
            iconPacks.values.toList().toIconPackModel()
        }

    override suspend fun getSuggestedIcons(app: AppModel): List<CustomIcon> =
        withContext(dispatcher) {
            fetchIconPacks()
            iconPacks.flatMap {
                it.value.getSuggestedIcons(context, app.packageName)
            }.toMutableList().apply {
                context.getApplicationIcon(app.packageName)?.let { stockIcon ->
                    CustomIcon(
                        drawable = stockIcon,
                        drawableName = app.packageName,
                        packageName = context.packageName
                    ).also {
                        CustomIconsDataCache.saveCached(it)
                        add(START_INDEX, it)
                    }
                }
            }
        }

    override suspend fun getAllIcons(iconPack: IconPackModel): List<CustomIcon> =
        withContext(dispatcher) {
            fetchIconPacks()
            iconPacks[iconPack.packageName]?.getAllIcons(context) ?: emptyList()
        }

    override suspend fun getIconPack(packageName: String): IconPackModel? {
        return iconPacks[packageName]?.toIconPackModel()
    }

    companion object {

        private const val START_INDEX = 0

        private fun List<IconPack>.toIconPackModel() = map {
            it.toIconPackModel()
        }

        private fun IconPack.toIconPackModel() = IconPackModel(
            id = UUID.randomUUID().toString(),
            label = label,
            packageName = packageName,
            activityName = activityName
        )

        private fun ResolveInfo.toIconPack(packageManager: PackageManager) = IconPack(
            label = loadLabel(packageManager).toString(),
            packageName = activityInfo.packageName,
            activityName = activityInfo.name
        )
    }

}
