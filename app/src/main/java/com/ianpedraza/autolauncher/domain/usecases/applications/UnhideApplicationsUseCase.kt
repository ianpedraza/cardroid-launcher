/*
 * HideApplicationsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 18/9/23 18:47
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.applications

import com.ianpedraza.autolauncher.data.repository.applications.ApplicationsRepository
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import javax.inject.Inject

class UnhideApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(apps: List<AppModel>) {
        repository.unhideApplications(apps)
    }
}
