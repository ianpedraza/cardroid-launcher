/*
 * SaveSystemApplicationsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 6/9/23 18:59
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.applications

import com.cardroidlauncher.app.data.repository.applications.ApplicationsRepository
import com.cardroidlauncher.app.domain.model.applications.AppModel
import javax.inject.Inject

class SaveSystemApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository,
) {
    suspend operator fun invoke(apps: List<AppModel>) {
        repository.saveSystemApplications(apps)
    }
}
