/*
 * DefaultIconPacksRepositoryTest.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 29/9/23 17:00
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.repository.iconpacks

import com.cardroidlauncher.app.MainCoroutineRule
import com.cardroidlauncher.app.data.datasource.iconpacks.IconPacksDataSource
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.mocks.ApplicationsMocks
import com.cardroidlauncher.app.mocks.IconPacksMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DefaultIconPacksRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val dataSource = mock<IconPacksDataSource>()

    private val repository = DefaultIconPacksRepository(dataSource, mainCoroutineRule.dispatcher)

    @Test
    fun `verify fetch icons call datasource with force true and success returns result`() =
        runTest {
            val iconPacks = IconPacksMocks.getIconPacksList()

            dataSource.stub {
                onBlocking { fetchIconPacks(any()) } doReturn iconPacks
            }

            val response = repository.fetchIconPacks()

            assertThat(response, IsEqual(iconPacks))
            verify(dataSource).fetchIconPacks(force = true)
        }

    @Test
    fun `verify fetch icons call datasource with force true and fails returns empty list`() =
        runTest {
            val exception = getException()

            dataSource.stub {
                onBlocking { fetchIconPacks(any()) } doThrow exception
            }

            val response = repository.fetchIconPacks()

            assertThat(response, IsEqual(emptyList()))
            verify(dataSource).fetchIconPacks(force = true)
        }


    @Test
    fun `verify get suggested icons call datasource and success returns result`() =
        runTest {
            val app = ApplicationsMocks.getApplicationModel()
            val icons = mock<List<CustomIcon>>()

            dataSource.stub {
                onBlocking { getSuggestedIcons(any()) } doReturn icons
            }

            val response = repository.getSuggestedIcons(app)

            assertThat(response, IsEqual(icons))
            verify(dataSource).getSuggestedIcons(app)
        }

    @Test
    fun `verify get suggested icons call datasource and fails returns empty list`() =
        runTest {
            val exception = getException()
            val app = ApplicationsMocks.getApplicationModel()

            dataSource.stub {
                onBlocking { getSuggestedIcons(any()) } doThrow exception
            }

            val response = repository.getSuggestedIcons(app)

            assertThat(response, IsEqual(emptyList()))
            verify(dataSource).getSuggestedIcons(app)
        }

    @Test
    fun `verify get all icons call datasource and success returns result`() =
        runTest {
            val iconPackModel = IconPacksMocks.getIconPackModel()
            val exception = getException()

            dataSource.stub {
                onBlocking { getAllIcons(any()) } doThrow exception
            }

            val response = repository.getAllIcons(iconPackModel)

            assertThat(response, IsEqual(emptyList()))
            verify(dataSource).getAllIcons(iconPackModel)
        }

    @Test
    fun `verify get all icons call datasource and fails returns empty list`() =
        runTest {
            val iconPackModel = IconPacksMocks.getIconPackModel()
            val icons = mock<List<CustomIcon>>()

            dataSource.stub {
                onBlocking { getAllIcons(any()) } doReturn icons
            }

            val response = repository.getAllIcons(iconPackModel)

            assertThat(response, IsEqual(icons))
            verify(dataSource).getAllIcons(iconPackModel)
        }

    @Test
    fun `verify get icon pack call datasource and success returns result`() =
        runTest {
            val packageName = "com.package.app"
            val iconPackModel = IconPacksMocks.getIconPackModel()

            dataSource.stub {
                onBlocking { getIconPack(any()) } doReturn iconPackModel
            }

            val response = repository.getIconPack(packageName)

            assertThat(response, IsEqual(iconPackModel))
            verify(dataSource).getIconPack(packageName)
        }


    @Test
    fun `verify get icon pack call datasource and fails returns null`() =
        runTest {
            val packageName = "com.package.app"
            val exception = getException()

            dataSource.stub {
                onBlocking { getIconPack(any()) } doThrow exception
            }

            val response = repository.getIconPack(packageName)

            assertThat(response, IsNull())
            verify(dataSource).getIconPack(packageName)
        }

    private fun getException() = MockitoException("error", Exception())
}
