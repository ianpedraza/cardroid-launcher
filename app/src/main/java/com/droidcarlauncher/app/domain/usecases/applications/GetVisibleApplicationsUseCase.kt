/*
 * GetVisibleApplicationsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 18/9/23 19:00
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.applications

import com.droidcarlauncher.app.data.repository.applications.ApplicationsRepository
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.domain.utils.FlowUtils.makeDataStateFlow
import com.droidcarlauncher.app.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVisibleApplicationsUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(): Flow<DataState<List<AppModel>>> = makeDataStateFlow {
        repository.getVisibleApplication()
    }
}
