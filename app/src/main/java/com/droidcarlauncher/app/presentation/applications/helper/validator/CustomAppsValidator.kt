/*
 * CustomAppsValidator.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 19/9/23 11:59
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.helper.validator

import com.droidcarlauncher.app.domain.model.applications.AppModel

interface CustomAppsValidator {
    fun validate(app: AppModel): Int?

    companion object {
        const val MAX_LENGTH = 30
    }
}
