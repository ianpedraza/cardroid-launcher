/*
 * CustomAppsDataValidator.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 19/9/23 11:59
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.helper.validator

import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.presentation.applications.resources.CustomAppsResourceManager
import javax.inject.Inject

class CustomAppsDataValidator @Inject constructor(
    private val resourceManager: CustomAppsResourceManager
) : CustomAppsValidator {

    override fun validate(app: AppModel): Int? {
        val customLabel = app.customLabel?.trim()
        return with(resourceManager) {
            when {
                customLabel != null && customLabel.isEmpty() -> emptyNameError()

                customLabel != null && customLabel.any {
                    it.isLetterOrDigit().not() && it.isWhitespace().not()
                } -> onlyLettersAndDigitsError()

                customLabel != null && customLabel.length > CustomAppsValidator.MAX_LENGTH -> maxLengthError()

                else -> null
            }
        }
    }

}
