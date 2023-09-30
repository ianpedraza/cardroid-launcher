/*
 * CustomAppsDataValidatorTest.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 29/9/23 18:00
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.helper.validator

import com.cardroidlauncher.app.domain.utils.Constants.EMPTY_STRING
import com.cardroidlauncher.app.mocks.ApplicationsMocks
import com.cardroidlauncher.app.presentation.applications.resources.CustomAppsResourceManager
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

@RunWith(MockitoJUnitRunner::class)
class CustomAppsDataValidatorTest {

    private val resources = mock<CustomAppsResourceManager> {
        on { emptyNameError() } doReturn EMPTY_ERROR_NAME_ERROR
        on { onlyLettersAndDigitsError() } doReturn ONLY_LETTERS_OR_DIGITS_ERROR
        on { maxLengthError() } doReturn MAX_LENGTH_ERROR
    }

    private val validator = CustomAppsDataValidator(resources)

    @Test
    fun `validate null label`() {
        val appModel = ApplicationsMocks.getApplicationModel().copy(
            customLabel = null
        )

        val result = validator.validate(appModel)

        assertThat(result, IsEqual(EMPTY_ERROR_NAME_ERROR))
        verify(resources).emptyNameError()
    }

    @Test
    fun `validate empty label`() {
        val appModel = ApplicationsMocks.getApplicationModel().copy(
            customLabel = EMPTY_STRING
        )

        val result = validator.validate(appModel)

        assertThat(result, IsEqual(EMPTY_ERROR_NAME_ERROR))
        verify(resources).emptyNameError()
    }

    @Test
    fun `validate only digits or letters label`() {
        val appModel = ApplicationsMocks.getApplicationModel().copy(
            customLabel = "Label *"
        )

        val result = validator.validate(appModel)

        assertThat(result, IsEqual(ONLY_LETTERS_OR_DIGITS_ERROR))
        verify(resources).onlyLettersAndDigitsError()
    }

    @Test
    fun `validate length of label`() {
        val appModel = ApplicationsMocks.getApplicationModel().copy(
            customLabel = "This is a very very very very very long label"
        )

        val result = validator.validate(appModel)

        assertThat(result, IsEqual(MAX_LENGTH_ERROR))
        verify(resources).maxLengthError()
    }

    @Test
    fun `validate valid label`() {
        val appModel = ApplicationsMocks.getApplicationModel().copy(
            customLabel = "Chrome"
        )

        val result = validator.validate(appModel)

        assertThat(result, IsNull())
        verifyNoInteractions(resources)
    }

    companion object {
        private const val EMPTY_ERROR_NAME_ERROR = 0
        private const val ONLY_LETTERS_OR_DIGITS_ERROR = 1
        private const val MAX_LENGTH_ERROR = 2
    }
}
