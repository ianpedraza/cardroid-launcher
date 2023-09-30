/*
 * EditApplicationUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 21/9/23 16:43
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.applications

import com.droidcarlauncher.app.data.repository.applications.ApplicationsRepository
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import javax.inject.Inject

class EditApplicationUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(app: AppModel, customIcon: CustomIcon?) {
        repository.editApplication(app, customIcon)
    }
}
