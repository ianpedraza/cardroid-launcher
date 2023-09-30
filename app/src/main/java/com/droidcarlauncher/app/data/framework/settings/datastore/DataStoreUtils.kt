/*
 * Extensions.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 19:19
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.settings.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreUtils {
    private const val DATA_STORE_NAME = "data-store"
    val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

    private const val SETTINGS_APPEARANCE_EARMARK = "settings-appearance-earmark"
    val settingsAppearancePreferencesKey = stringPreferencesKey(SETTINGS_APPEARANCE_EARMARK)

    private const val SETTINGS_STANDBY_EARMARK = "settings-standby-earmark"
    val settingsStandbyPreferencesKey = stringPreferencesKey(SETTINGS_STANDBY_EARMARK)

    private const val SETTINGS_WALLPAPER_EARMARK = "settings-wallpaper-earmark"
    val settingsWallpaperPreferencesKey = stringPreferencesKey(SETTINGS_WALLPAPER_EARMARK)

    private const val SETTINGS_GENERAL_EARMARK = "settings-general-earmark"
    val settingsGeneralPreferencesKey = stringPreferencesKey(SETTINGS_GENERAL_EARMARK)
}
