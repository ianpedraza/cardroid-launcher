/*
 * DefaultApplicationsRepositoryTest.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 29/9/23 14:11
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.repository.applications

import com.droidcarlauncher.app.MainCoroutineRule
import com.droidcarlauncher.app.data.datasource.applications.ApplicationsDataSource
import com.droidcarlauncher.app.data.framework.iconpacks.manager.cache.CustomIconsCache
import com.droidcarlauncher.app.data.framework.iconpacks.manager.storage.IconStorageManager
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import com.droidcarlauncher.app.mocks.ApplicationsMocks
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
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.verifyNoMoreInteractions

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DefaultApplicationsRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private val dataSource = mock<ApplicationsDataSource>()

    private val iconStorageManager = mock<IconStorageManager>()

    private val customIconsCache = mock<CustomIconsCache>()

    private val repository = DefaultApplicationsRepository(
        dataSource,
        iconStorageManager,
        customIconsCache,
        mainCoroutineRule.dispatcher
    )

    @Test
    fun `save system applications whe db is empty`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()

        dataSource.stub {
            onBlocking { getApplications() } doReturn emptyList()
        }

        repository.saveSystemApplications(appsModelList)

        verify(dataSource).insert(appsModelList)
    }

    @Test
    fun `save system applications whe db is not empty`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()

        val newApp = ApplicationsMocks.getApplicationModel()

        val systemApps = appsModelList.toMutableList().apply {
            add(newApp)
        }.toList()

        val lastPosition = appsModelList.maxByOrNull { it.position }?.position ?: INITIAL_POSITION
        val expected = listOf(newApp.copy(position = lastPosition + 1))

        dataSource.stub {
            onBlocking { getApplications() } doReturn appsModelList
        }

        repository.saveSystemApplications(systemApps)

        verify(dataSource).insert(expected)
    }

    @Test
    fun `get application should return list of app model`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()

        dataSource.stub {
            onBlocking { observeApplications() } doReturn flowOf(appsModelList)
        }

        val result = repository.getAllApplications().last()

        assertThat(result, IsEqual(appsModelList))
        verify(dataSource).observeApplications()
    }

    @Test
    fun `get application should return list of app model with hidden false`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()

        dataSource.stub {
            onBlocking { observeApplications() } doReturn flowOf(appsModelList)
        }

        val result = repository.getVisibleApplication().last()

        assertThat(result.all { it.hidden.not() }, IsEqual(true))
        verify(dataSource).observeApplications()
    }

    @Test
    fun `move applications should move all apps`() = runTest {
        val app1 = ApplicationsMocks.getApplicationModel()
        val app2 = ApplicationsMocks.getApplicationModel()
        val app3 = ApplicationsMocks.getApplicationModel()

        val currentState = listOf(
            app1.copy(position = 0),
            app2.copy(position = 1),
            app3.copy(position = 2),
        )

        val expectedState = listOf(
            app3.copy(position = 0),
            app1.copy(position = 1),
            app2.copy(position = 2),
        )

        dataSource.stub {
            onBlocking { getApplications() } doReturn currentState
        }

        repository.moveApplications(2, 0)

        verify(dataSource).update(expectedState)
    }

    @Test
    fun `remove applications should call data source`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()

        dataSource.stub {
            onBlocking { remove(any()) } doReturn Unit
        }

        repository.removeApplications(appsModelList)

        verify(dataSource).remove(appsModelList)
    }

    @Test
    fun `hide applications should set hidden true to all apps`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList().map {
            it.copy(hidden = false)
        }

        val expected = appsModelList.map { it.copy(hidden = true) }

        dataSource.stub {
            onBlocking { update(any()) } doReturn Unit
        }

        repository.hideApplications(appsModelList)

        verify(dataSource).update(expected)
    }

    @Test
    fun `unhide applications should set hidden false to all apps`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList().map {
            it.copy(hidden = true)
        }

        val expected = appsModelList.map { it.copy(hidden = false) }

        dataSource.stub {
            onBlocking { update(any()) } doReturn Unit
        }

        repository.unhideApplications(appsModelList)

        verify(dataSource).update(expected)
    }

    @Test
    fun `edit application with custom icon null`() = runTest {
        val oldIconName = null
        val newIconName = "icon-name"

        val appsModelList = ApplicationsMocks.getApplicationsModelList()
        val customIcon = null

        val baseApp = ApplicationsMocks.getApplicationModel()
        val currentApplicationInDb = baseApp.copy(
            customIconName = oldIconName
        )
        val app = baseApp.copy(customIconName = newIconName)
        val expectedApp = baseApp.copy(customIconName = newIconName)

        dataSource.stub {
            onBlocking { getApplication(any()) } doReturn currentApplicationInDb
            onBlocking { getApplications() } doReturn appsModelList
        }

        repository.editApplication(app, customIcon)
        verify(dataSource).update(listOf(expectedApp))
        verifyNoInteractions(iconStorageManager)
        verifyNoInteractions(customIconsCache)
    }

    @Test
    fun `edit application with new custom icon no system icon and previous different and save success should remove previous`() =
        runTest {
            val oldIconName = "old-icon-name"
            val newIconName = "icon-name"

            val customIcon = mock<CustomIcon> {
                on { iconName } doReturn newIconName
            }

            val appsModelList = ApplicationsMocks.getApplicationsModelList()
            val baseApp = ApplicationsMocks.getApplicationModel()
            val currentApplicationInDb = baseApp.copy(
                customIconName = oldIconName
            )
            val app = baseApp.copy(customIconName = newIconName)
            val expectedApp = baseApp.copy(customIconName = newIconName)

            dataSource.stub {
                onBlocking { getApplication(any()) } doReturn currentApplicationInDb
                onBlocking { getApplications() } doReturn appsModelList
            }

            iconStorageManager.stub {
                on { remove(any()) } doReturn true
                on { isSystemStockIcon(any()) } doReturn false
                on { save(any()) } doReturn true
            }

            repository.editApplication(app, customIcon)

            verify(iconStorageManager).remove(oldIconName)
            verify(customIconsCache).removeCached(oldIconName)

            verify(iconStorageManager).isSystemStockIcon(newIconName)
            verify(iconStorageManager).save(customIcon)

            verifyNoMoreInteractions(iconStorageManager)
            verifyNoMoreInteractions(customIconsCache)

            verify(dataSource).update(listOf(expectedApp))
        }


    @Test
    fun `edit application with new custom icon no system icon and previous different and save fails should remove previous and keep current icon`() =
        runTest {
            val oldIconName = "old-icon-name"
            val newIconName = "icon-name"

            val customIcon = mock<CustomIcon> {
                on { iconName } doReturn newIconName
            }

            val appsModelList = ApplicationsMocks.getApplicationsModelList()
            val baseApp = ApplicationsMocks.getApplicationModel()
            val currentApplicationInDb = baseApp.copy(
                customIconName = oldIconName
            )
            val app = baseApp.copy(customIconName = newIconName)
            val expectedApp = baseApp.copy(customIconName = oldIconName)

            dataSource.stub {
                onBlocking { getApplication(any()) } doReturn currentApplicationInDb
                onBlocking { getApplications() } doReturn appsModelList
            }

            iconStorageManager.stub {
                on { remove(any()) } doReturn true
                on { isSystemStockIcon(any()) } doReturn false
                on { save(any()) } doReturn false
            }

            repository.editApplication(app, customIcon)

            verify(iconStorageManager).remove(oldIconName)
            verify(customIconsCache).removeCached(oldIconName)

            verify(iconStorageManager).isSystemStockIcon(newIconName)
            verify(iconStorageManager).save(customIcon)

            verifyNoMoreInteractions(iconStorageManager)
            verifyNoMoreInteractions(customIconsCache)

            verify(dataSource).update(listOf(expectedApp))
        }

    @Test
    fun `edit application with new custom icon is system icon and previous different and save success should remove previous`() =
        runTest {
            val oldIconName = "old-icon-name"
            val newIconName = "icon-name"

            val customIcon = mock<CustomIcon> {
                on { iconName } doReturn newIconName
            }

            val appsModelList = ApplicationsMocks.getApplicationsModelList()
            val baseApp = ApplicationsMocks.getApplicationModel()
            val currentApplicationInDb = baseApp.copy(
                customIconName = oldIconName
            )
            val app = baseApp.copy(customIconName = newIconName)
            val expectedApp = baseApp.copy(customIconName = newIconName)

            dataSource.stub {
                onBlocking { getApplication(any()) } doReturn currentApplicationInDb
                onBlocking { getApplications() } doReturn appsModelList
            }

            iconStorageManager.stub {
                on { remove(any()) } doReturn true
                on { isSystemStockIcon(any()) } doReturn true
                on { save(any()) } doReturn true
            }

            repository.editApplication(app, customIcon)

            verify(iconStorageManager).remove(oldIconName)
            verify(customIconsCache).removeCached(oldIconName)

            verify(iconStorageManager).isSystemStockIcon(newIconName)
            verify(iconStorageManager).remove(newIconName)
            verify(customIconsCache).removeCached(newIconName)
            verify(iconStorageManager).save(customIcon)

            verifyNoMoreInteractions(iconStorageManager)
            verifyNoMoreInteractions(customIconsCache)

            verify(dataSource).update(listOf(expectedApp))
        }

    @Test
    fun `edit application with new custom icon is system icon and previous different and save fails should remove previous`() =
        runTest {
            val oldIconName = "old-icon-name"
            val newIconName = "icon-name"

            val customIcon = mock<CustomIcon> {
                on { iconName } doReturn newIconName
            }

            val appsModelList = ApplicationsMocks.getApplicationsModelList()
            val baseApp = ApplicationsMocks.getApplicationModel()
            val currentApplicationInDb = baseApp.copy(
                customIconName = oldIconName
            )
            val app = baseApp.copy(customIconName = newIconName)
            val expectedApp = baseApp.copy(customIconName = oldIconName)

            dataSource.stub {
                onBlocking { getApplication(any()) } doReturn currentApplicationInDb
                onBlocking { getApplications() } doReturn appsModelList
            }

            iconStorageManager.stub {
                on { remove(any()) } doReturn true
                on { isSystemStockIcon(any()) } doReturn true
                on { save(any()) } doReturn false
            }

            repository.editApplication(app, customIcon)

            verify(iconStorageManager).remove(oldIconName)
            verify(customIconsCache).removeCached(oldIconName)

            verify(iconStorageManager).isSystemStockIcon(newIconName)
            verify(iconStorageManager).remove(newIconName)
            verify(customIconsCache).removeCached(newIconName)
            verify(iconStorageManager).save(customIcon)

            verifyNoMoreInteractions(iconStorageManager)
            verifyNoMoreInteractions(customIconsCache)

            verify(dataSource).update(listOf(expectedApp))
        }

    @Test
    fun `reset icons should clear all custom icons and labels`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()

        val expected = appsModelList.map {
            it.copy(
                customIconName = null,
                customLabel = null
            )
        }

        dataSource.stub {
            onBlocking { getApplications() } doReturn appsModelList
        }

        repository.resetIcons()

        verify(customIconsCache).clear()
        verify(iconStorageManager).clear()
        verify(dataSource).update(expected)
    }

    companion object {
        private const val INITIAL_POSITION = 0
    }
}
