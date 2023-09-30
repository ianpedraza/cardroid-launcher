/*
 * CustomAppsResourceDataManager.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 25/9/23 14:15
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.resources

import com.cardroidlauncher.app.R
import javax.inject.Inject

class CustomAppsResourceDataManager @Inject constructor() : CustomAppsResourceManager {

    override fun emptyNameError(): Int = R.string.e_empty_name

    override fun onlyLettersAndDigitsError(): Int = R.string.e_only_letters_or_digits

    override fun maxLengthError(): Int = R.string.e_max_length
}
