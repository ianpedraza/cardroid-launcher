/*
 * HideApplicationsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 18/9/23 18:47
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.applications

import com.droidcarlauncher.app.data.repository.applications.ApplicationsRepository
import com.droidcarlauncher.app.domain.model.applications.AppModel
import javax.inject.Inject

class HideApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(apps: List<AppModel>) {
        repository.hideApplications(apps)
    }
}