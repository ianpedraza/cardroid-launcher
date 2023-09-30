/*
 * GetAllApplicationsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 6/9/23 19:07
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.applications

import com.ianpedraza.autolauncher.data.repository.applications.ApplicationsRepository
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.utils.FlowUtils.makeDataStateFlow
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository,
) {
    suspend operator fun invoke(): Flow<DataState<List<AppModel>>> = makeDataStateFlow {
        repository.getAllApplications()
    }
}
