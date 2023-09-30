/*
 * GetAllApplicationsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 6/9/23 19:07
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.applications

import com.cardroidlauncher.app.data.repository.applications.ApplicationsRepository
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.utils.FlowUtils.makeDataStateFlow
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository,
) {
    suspend operator fun invoke(): Flow<DataState<List<AppModel>>> = makeDataStateFlow {
        repository.getAllApplications()
    }
}
