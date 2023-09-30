/*
 * PlayStoreSearchHelper.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 28/9/23 14:59
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.helper.playstore

interface PlayStoreSearchHelper {
    fun createUrl(query: String): String
}
