/*
 * SwapApplicationsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 7/9/23 13:24
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.applications

import com.ianpedraza.autolauncher.data.repository.applications.ApplicationsRepository
import javax.inject.Inject

class MoveApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository,
) {
    suspend operator fun invoke(originIndex: Int, targetIndex: Int) {
        repository.moveApplications(originIndex, targetIndex)
    }
}
