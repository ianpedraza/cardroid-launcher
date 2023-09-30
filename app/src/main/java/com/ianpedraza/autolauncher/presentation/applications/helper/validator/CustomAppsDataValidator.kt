/*
 * CustomAppsDataValidator.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 19/9/23 11:59
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.applications.helper.validator

import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.presentation.applications.resources.CustomAppsResourceManager
import javax.inject.Inject

class CustomAppsDataValidator @Inject constructor(
    private val resourceManager: CustomAppsResourceManager
) : CustomAppsValidator {

    override fun validate(app: AppModel): Int? {
        val customLabel = app.customLabel?.trim()
        return with(resourceManager) {
            when {
                customLabel.isNullOrEmpty() -> emptyNameError()

                customLabel.any {
                    it.isLetterOrDigit().not() && it.isWhitespace().not()
                } -> onlyLettersAndDigitsError()

                customLabel.length > CustomAppsValidator.MAX_LENGTH -> maxLengthError()

                else -> null
            }
        }
    }

}
