/*
 * EditApplicationUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 21/9/23 16:43
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.applications

import com.cardroidlauncher.app.data.repository.applications.ApplicationsRepository
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import javax.inject.Inject

class EditApplicationUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(app: AppModel, customIcon: CustomIcon?) {
        repository.editApplication(app, customIcon)
    }
}
