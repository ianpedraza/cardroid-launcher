/*
 * RoomApplicationsDataSourceTest.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 29/9/23 12:18
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.applications

import com.ianpedraza.autolauncher.MainCoroutineRule
import com.ianpedraza.autolauncher.data.framework.applications.room.AppsDao
import com.ianpedraza.autolauncher.mocks.ApplicationsMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.StandardTestDispatcher
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RoomApplicationsDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val appsDao = mock<AppsDao>()
    private val dataSource = RoomApplicationsDataSource(appsDao, mainCoroutineRule.dispatcher)

    @Test
    fun `observe applications should return app model list`() = runTest {
        val appsEntityList = ApplicationsMocks.getApplicationsEntityList()

        appsDao.stub {
            on { observeApps() } doReturn flowOf(appsEntityList)
        }

        val result = dataSource.observeApplications().last()

        assertThat(result.size, IsEqual(appsEntityList.size))
        verify(appsDao).observeApps()
    }

    @Test
    fun `get applications should return app model list`() = runTest {
        val appsEntityList = ApplicationsMocks.getApplicationsEntityList()

        appsDao.stub {
            onBlocking { getAll() } doReturn appsEntityList
        }

        val result = dataSource.getApplications()

        assertThat(result.size, IsEqual(appsEntityList.size))
        verify(appsDao).getAll()
    }


    @Test
    fun `insert should call dao's insert`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()
        val appsEntityList = ApplicationsMocks.getApplicationsEntityList()

        appsDao.stub {
            onBlocking { insert(any()) } doReturn Unit
        }

        dataSource.insert(appsModelList)

        verify(appsDao).insert(appsEntityList)
    }

    @Test
    fun `update should call dao's update`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()
        val appsEntityList = ApplicationsMocks.getApplicationsEntityList()

        appsDao.stub {
            onBlocking { update(any()) } doReturn Unit
        }

        dataSource.update(appsModelList)

        verify(appsDao).update(appsEntityList)
    }

    @Test
    fun `remove should call dao's remove`() = runTest {
        val appsModelList = ApplicationsMocks.getApplicationsModelList()
        val appsEntityList = ApplicationsMocks.getApplicationsEntityList()

        appsDao.stub {
            onBlocking { remove(any()) } doReturn Unit
        }

        dataSource.remove(appsModelList)

        verify(appsDao).remove(appsEntityList)
    }
}
