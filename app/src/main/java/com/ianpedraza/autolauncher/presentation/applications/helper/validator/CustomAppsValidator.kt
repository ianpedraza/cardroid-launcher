/*
 * CustomAppsValidator.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 19/9/23 11:59
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.helper.validator

import com.ianpedraza.autolauncher.domain.model.applications.AppModel

interface CustomAppsValidator {
    fun validate(app: AppModel): Int?

    companion object {
        const val MAX_LENGTH = 30
    }
}
