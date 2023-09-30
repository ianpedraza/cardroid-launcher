/*
 * PlayStoreSearchDataHelper.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 28/9/23 14:59
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.helper.playstore

import javax.inject.Inject

class PlayStoreSearchDataHelper @Inject constructor() : PlayStoreSearchHelper {

    override fun createUrl(query: String): String {
        return PLAY_STORE_BASE_URL.replace(PARAM_QUERY, query)
    }

    companion object {
        private const val PARAM_QUERY = "{query}"
        private const val PLAY_STORE_BASE_URL =
            "https://play.google.com/store/search?q=$PARAM_QUERY&c=apps"
    }
}
