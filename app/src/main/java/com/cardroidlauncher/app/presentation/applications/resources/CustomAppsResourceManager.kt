/*
 * CustomAppsResourceManager.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 25/9/23 14:05
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.resources

interface CustomAppsResourceManager {
    fun emptyNameError(): Int
    fun onlyLettersAndDigitsError(): Int
    fun maxLengthError(): Int
}
