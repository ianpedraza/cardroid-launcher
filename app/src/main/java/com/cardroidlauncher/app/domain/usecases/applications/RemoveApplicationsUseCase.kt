/*
 * RemoveApplicationsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 18/9/23 17:39
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.applications

import com.cardroidlauncher.app.data.repository.applications.ApplicationsRepository
import com.cardroidlauncher.app.domain.model.applications.AppModel
import javax.inject.Inject

class RemoveApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(apps: List<AppModel>) {
        repository.removeApplications(apps)
    }
}
