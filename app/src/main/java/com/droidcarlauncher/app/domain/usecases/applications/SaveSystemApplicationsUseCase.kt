/*
 * SaveSystemApplicationsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 6/9/23 18:59
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.applications

import com.droidcarlauncher.app.data.repository.applications.ApplicationsRepository
import com.droidcarlauncher.app.domain.model.applications.AppModel
import javax.inject.Inject

class SaveSystemApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository,
) {
    suspend operator fun invoke(apps: List<AppModel>) {
        repository.saveSystemApplications(apps)
    }
}
