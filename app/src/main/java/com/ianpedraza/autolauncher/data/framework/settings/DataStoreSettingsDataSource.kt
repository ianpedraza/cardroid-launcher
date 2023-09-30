/*
 * DataStoreSettingsDataSource.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 19:18
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import com.ianpedraza.autolauncher.data.datasource.settings.SettingsDataSource
import com.ianpedraza.autolauncher.data.framework.settings.datastore.DataStoreUtils.settingsAppearancePreferencesKey
import com.ianpedraza.autolauncher.data.framework.settings.datastore.DataStoreUtils.settingsGeneralPreferencesKey
import com.ianpedraza.autolauncher.data.framework.settings.datastore.DataStoreUtils.settingsStandbyPreferencesKey
import com.ianpedraza.autolauncher.data.framework.settings.datastore.DataStoreUtils.settingsWallpaperPreferencesKey
import com.ianpedraza.autolauncher.di.IoDispatcher
import com.ianpedraza.autolauncher.di.SettingsGson
import com.ianpedraza.autolauncher.domain.model.settings.appearance.AppearanceSettings
import com.ianpedraza.autolauncher.domain.model.settings.general.GeneralSettings
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbySettings
import com.ianpedraza.autolauncher.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataStoreSettingsDataSource @Inject constructor(
    @SettingsGson private val gson: Gson,
    private val dataStore: DataStore<Preferences>,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : SettingsDataSource {

    override suspend fun saveAppearanceSettings(settings: AppearanceSettings): Unit =
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[settingsAppearancePreferencesKey] = gson.toJson(settings)
            }
        }

    override suspend fun getAppearanceSettings(): Flow<AppearanceSettings> =
        withContext(dispatcher) {
            dataStore.data.map { preferences ->
                preferences[settingsAppearancePreferencesKey]?.let { json ->
                    gson.fromJson(json, AppearanceSettings::class.java)
                } ?: AppearanceSettings()
            }
        }

    override suspend fun saveStandbySettings(settings: StandbySettings): Unit =
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[settingsStandbyPreferencesKey] = gson.toJson(settings)
            }
        }

    override suspend fun getStandbySettings(): Flow<StandbySettings> = withContext(dispatcher) {
        dataStore.data.map { preferences ->
            preferences[settingsStandbyPreferencesKey]?.let { json ->
                gson.fromJson(json, StandbySettings::class.java)
            } ?: StandbySettings()
        }
    }

    override suspend fun saveWallpaperSettings(settings: WallpaperSettings) {
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[settingsWallpaperPreferencesKey] = gson.toJson(settings)
            }
        }
    }

    override suspend fun getWallpaperSettings(): Flow<WallpaperSettings> = withContext(dispatcher) {
        dataStore.data.map { preferences ->
            preferences[settingsWallpaperPreferencesKey]?.let { json ->
                gson.fromJson(json, WallpaperSettings::class.java)
            } ?: WallpaperSettings()
        }
    }

    override suspend fun saveGeneralSettings(settings: GeneralSettings) {
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[settingsGeneralPreferencesKey] = gson.toJson(settings)
            }
        }
    }

    override suspend fun getGeneralSettings(): Flow<GeneralSettings> = withContext(dispatcher) {
        dataStore.data.map { preferences ->
            preferences[settingsGeneralPreferencesKey]?.let { json ->
                gson.fromJson(json, GeneralSettings::class.java)
            } ?: GeneralSettings()
        }
    }
}
