/*
 * CustomAppsResourceManager.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 25/9/23 14:05
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.resources

interface CustomAppsResourceManager {
    fun emptyNameError(): Int
    fun onlyLettersAndDigitsError(): Int
    fun maxLengthError(): Int
}
