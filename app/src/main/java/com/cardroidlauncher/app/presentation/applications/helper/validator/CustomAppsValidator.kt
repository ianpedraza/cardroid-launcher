/*
 * CustomAppsValidator.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 19/9/23 11:59
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.helper.validator

import com.cardroidlauncher.app.domain.model.applications.AppModel

interface CustomAppsValidator {
    fun validate(app: AppModel): Int?

    companion object {
        const val MAX_LENGTH = 30
    }
}
