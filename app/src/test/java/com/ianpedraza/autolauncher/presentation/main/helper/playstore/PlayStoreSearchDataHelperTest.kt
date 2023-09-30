/*
 * PlayStoreSearchDataHelperTest.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 29/9/23 18:10
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.helper.playstore

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlayStoreSearchDataHelperTest {

    private val helper = PlayStoreSearchDataHelper()

    @Test
    fun `test create url`() {
        val query = "icon packs"
        val expected = "https://play.google.com/store/search?q=icon packs&c=apps"

        val result = helper.createUrl(query)
        assertThat(result, IsEqual(expected))
    }

}
