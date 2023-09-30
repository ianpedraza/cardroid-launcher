/*
 * PlayStoreSearchHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 28/9/23 14:59
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.helper.playstore

interface PlayStoreSearchHelper {
    fun createUrl(query: String): String
}
