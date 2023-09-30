/*
 * DataModule.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 6/9/23 17:46
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ianpedraza.autolauncher.data.datasource.applications.ApplicationsDataSource
import com.ianpedraza.autolauncher.data.datasource.iconpacks.IconPacksDataSource
import com.ianpedraza.autolauncher.data.datasource.settings.SettingsDataSource
import com.ianpedraza.autolauncher.data.framework.applications.RoomApplicationsDataSource
import com.ianpedraza.autolauncher.data.framework.applications.room.AppsDao
import com.ianpedraza.autolauncher.data.framework.applications.room.AppsDb
import com.ianpedraza.autolauncher.data.framework.iconpacks.SystemIconPacksDataSource
import com.ianpedraza.autolauncher.data.framework.iconpacks.manager.cache.CustomIconsCache
import com.ianpedraza.autolauncher.data.framework.iconpacks.manager.cache.CustomIconsDataCache
import com.ianpedraza.autolauncher.data.framework.iconpacks.manager.storage.IconStorageDataManager
import com.ianpedraza.autolauncher.data.framework.iconpacks.manager.storage.IconStorageManager
import com.ianpedraza.autolauncher.data.framework.settings.DataStoreSettingsDataSource
import com.ianpedraza.autolauncher.data.framework.settings.datastore.DataStoreUtils.dataStore
import com.ianpedraza.autolauncher.data.repository.applications.ApplicationsRepository
import com.ianpedraza.autolauncher.data.repository.applications.DefaultApplicationsRepository
import com.ianpedraza.autolauncher.data.repository.iconpacks.DefaultIconPacksRepository
import com.ianpedraza.autolauncher.data.repository.iconpacks.IconPacksRepository
import com.ianpedraza.autolauncher.data.repository.settings.DefaultSettingsRepository
import com.ianpedraza.autolauncher.data.repository.settings.SettingsRepository
import com.ianpedraza.autolauncher.domain.model.settings.appearance.darkheme.DarkThemeMode
import com.ianpedraza.autolauncher.domain.model.settings.appearance.darkheme.DarkThemeModeAdapter
import com.ianpedraza.autolauncher.domain.model.settings.clockformat.ClockFormat
import com.ianpedraza.autolauncher.domain.model.settings.clockformat.ClockFormatAdapter
import com.ianpedraza.autolauncher.domain.model.settings.general.orientation.ScreenOrientation
import com.ianpedraza.autolauncher.domain.model.settings.general.orientation.ScreenOrientationAdapter
import com.ianpedraza.autolauncher.domain.model.settings.general.steeringwheelposition.SteeringWheelPosition
import com.ianpedraza.autolauncher.domain.model.settings.general.steeringwheelposition.SteeringWheelPositionAdapter
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbyMode
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbyModeAdapter
import com.ianpedraza.autolauncher.domain.model.settings.wallpaper.Wallpaper
import com.ianpedraza.autolauncher.domain.model.settings.wallpaper.WallpaperAdapter
import com.ianpedraza.autolauncher.presentation.applications.helper.systemapps.SystemAppsDataHelper
import com.ianpedraza.autolauncher.presentation.applications.helper.systemapps.SystemAppsHelper
import com.ianpedraza.autolauncher.presentation.applications.helper.validator.CustomAppsDataValidator
import com.ianpedraza.autolauncher.presentation.applications.helper.validator.CustomAppsValidator
import com.ianpedraza.autolauncher.presentation.applications.resources.CustomAppsResourceDataManager
import com.ianpedraza.autolauncher.presentation.applications.resources.CustomAppsResourceManager
import com.ianpedraza.autolauncher.presentation.main.helper.playstore.PlayStoreSearchDataHelper
import com.ianpedraza.autolauncher.presentation.main.helper.playstore.PlayStoreSearchHelper
import com.ianpedraza.autolauncher.presentation.main.helper.standby.StandbyDataHelper
import com.ianpedraza.autolauncher.presentation.main.helper.standby.StandbyHelper
import com.ianpedraza.autolauncher.presentation.settings.ui.screens.appearance.iconpack.helper.IconPackDataHelper
import com.ianpedraza.autolauncher.presentation.settings.ui.screens.appearance.iconpack.helper.IconPackHelper
import com.ianpedraza.autolauncher.presentation.settings.ui.screens.wallpaper.helper.WallpaperDetailDataHelper
import com.ianpedraza.autolauncher.presentation.settings.ui.screens.wallpaper.helper.WallpaperDetailHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideApplicationsDataSource(
        dataSource: RoomApplicationsDataSource,
    ): ApplicationsDataSource

    @Singleton
    @Binds
    abstract fun provideSettingsDataSource(
        dataSource: DataStoreSettingsDataSource,
    ): SettingsDataSource

    @Singleton
    @Binds
    abstract fun provideApplicationsRepository(
        repository: DefaultApplicationsRepository,
    ): ApplicationsRepository

    @Singleton
    @Binds
    abstract fun provideSettingsRepository(
        repository: DefaultSettingsRepository,
    ): SettingsRepository

    @Singleton
    @Binds
    abstract fun provideStandbyHelper(
        helper: StandbyDataHelper,
    ): StandbyHelper

    @Singleton
    @Binds
    abstract fun provideCustomAppsResourceManager(
        manager: CustomAppsResourceDataManager,
    ): CustomAppsResourceManager

    @Singleton
    @Binds
    abstract fun provideCustomAppsValidator(
        validator: CustomAppsDataValidator,
    ): CustomAppsValidator

    @Singleton
    @Binds
    abstract fun provideSystemAppsHelper(
        helper: SystemAppsDataHelper,
    ): SystemAppsHelper

    @Singleton
    @Binds
    abstract fun provideIconPacksDataSource(
        dataSource: SystemIconPacksDataSource,
    ): IconPacksDataSource

    @Singleton
    @Binds
    abstract fun provideIconPacksRepository(
        repository: DefaultIconPacksRepository,
    ): IconPacksRepository

    @Singleton
    @Binds
    abstract fun provideIconStorageManager(
        manager: IconStorageDataManager,
    ): IconStorageManager

    @Singleton
    @Binds
    abstract fun provideIconPackHelper(
        instance: IconPackDataHelper,
    ): IconPackHelper

    @Singleton
    @Binds
    abstract fun providePlayStoreSearchHelper(
        helper: PlayStoreSearchDataHelper,
    ): PlayStoreSearchHelper

    @Singleton
    @Binds
    abstract fun provideWallpaperDetailHelper(
        helper: WallpaperDetailDataHelper,
    ): WallpaperDetailHelper

    companion object {

        @Singleton
        @Provides
        fun provideCustomIconsCache(): CustomIconsCache = CustomIconsDataCache

        @Singleton
        @Provides
        fun provideAppsDb(
            @ApplicationContext context: Context,
        ): AppsDb = Room.databaseBuilder(context, AppsDb::class.java, "apps").build()

        @Singleton
        @Provides
        fun provideDataStorePreferences(
            @ApplicationContext context: Context,
        ): DataStore<Preferences> = context.dataStore

        @Singleton
        @Provides
        fun provideAppsDao(
            appsDb: AppsDb,
        ): AppsDao = appsDb.appsDao()

        @Provides
        @SettingsGson
        fun provideSettingsGson(): Gson = GsonBuilder()
            .registerTypeAdapter(DarkThemeMode::class.java, DarkThemeModeAdapter())
            .registerTypeAdapter(StandbyMode::class.java, StandbyModeAdapter())
            .registerTypeAdapter(Wallpaper::class.java, WallpaperAdapter())
            .registerTypeAdapter(ScreenOrientation::class.java, ScreenOrientationAdapter())
            .registerTypeAdapter(SteeringWheelPosition::class.java, SteeringWheelPositionAdapter())
            .registerTypeAdapter(ClockFormat::class.java, ClockFormatAdapter())
            .create()

        @Provides
        @SimpleGson
        fun provideGson(): Gson = Gson()

        @Provides
        @IoDispatcher
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @DefaultDispatcher
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        @MainDispatcher
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    }
}
