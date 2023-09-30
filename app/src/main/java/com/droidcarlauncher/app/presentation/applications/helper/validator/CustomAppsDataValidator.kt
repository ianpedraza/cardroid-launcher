/*
 * CustomAppsDataValidator.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 19/9/23 11:59
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.helper.validator

import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.applications.resources.CustomAppsResourceManager
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
