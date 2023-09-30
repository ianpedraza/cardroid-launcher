/*
 * PlayStoreSearchHelper.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 28/9/23 14:59
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.helper.playstore

interface PlayStoreSearchHelper {
    fun createUrl(query: String): String
}
