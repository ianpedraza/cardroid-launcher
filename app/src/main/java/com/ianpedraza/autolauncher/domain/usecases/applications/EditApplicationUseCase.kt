/*
 * EditApplicationUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 21/9/23 16:43
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.applications

import com.ianpedraza.autolauncher.data.repository.applications.ApplicationsRepository
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import javax.inject.Inject

class EditApplicationUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(app: AppModel, customIcon: CustomIcon?) {
        repository.editApplication(app, customIcon)
    }
}
