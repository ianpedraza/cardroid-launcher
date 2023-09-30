/*
 * DefaultSettingsRepositoryTest.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 29/9/23 17:22
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.repository.settings

import com.ianpedraza.autolauncher.MainCoroutineRule
import com.ianpedraza.autolauncher.data.datasource.settings.SettingsDataSource
import com.ianpedraza.autolauncher.domain.model.settings.appearance.AppearanceSettings
import com.ianpedraza.autolauncher.domain.model.settings.general.GeneralSettings
import com.ianpedraza.autolauncher.domain.model.settings.standby.StandbySettings
import com.ianpedraza.autolauncher.domain.model.settings.wallpaper.WallpaperSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DefaultSettingsRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val dataSource = mock<SettingsDataSource>()

    private val repository = DefaultSettingsRepository(dataSource, mainCoroutineRule.dispatcher)

    @Test
    fun `test save standby settings should calls data source`() = runTest {
        val settings = StandbySettings()
        repository.saveStandbySettings(settings)
        verify(dataSource).saveStandbySettings(settings)
    }

    @Test
    fun `test get standby settings should return settings flow`() = runTest {
        val settings = StandbySettings()

        dataSource.stub {
            onBlocking { getStandbySettings() } doReturn flowOf(settings)
        }

        val result = repository.getStandbySettings().last()

        assertThat(result, IsEqual(settings))
        verify(dataSource).getStandbySettings()
    }

    @Test
    fun `test save wallpaper settings should calls data source`() = runTest {
        val settings = WallpaperSettings()
        repository.saveWallpaperSettings(settings)
        verify(dataSource).saveWallpaperSettings(settings)
    }

    @Test
    fun `test get wallpaper settings should return settings flow`() = runTest {
        val settings = WallpaperSettings()

        dataSource.stub {
            onBlocking { getWallpaperSettings() } doReturn flowOf(settings)
        }

        val result = repository.getWallpaperSettings().last()

        assertThat(result, IsEqual(settings))
        verify(dataSource).getWallpaperSettings()
    }

    @Test
    fun `test save general settings should calls data source`() = runTest {
        val settings = GeneralSettings()
        repository.saveGeneralSettings(settings)
        verify(dataSource).saveGeneralSettings(settings)
    }

    @Test
    fun `test get general settings should return settings flow`() = runTest {
        val settings = GeneralSettings()

        dataSource.stub {
            onBlocking { getGeneralSettings() } doReturn flowOf(settings)
        }

        val result = repository.getGeneralSettings().last()

        assertThat(result, IsEqual(settings))
        verify(dataSource).getGeneralSettings()
    }
}
