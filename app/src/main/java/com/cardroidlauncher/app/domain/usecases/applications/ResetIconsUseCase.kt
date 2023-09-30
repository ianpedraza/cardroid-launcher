/*
 * ResetIconsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 27/9/23 12:16
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.applications

import com.cardroidlauncher.app.data.repository.applications.ApplicationsRepository
import com.cardroidlauncher.app.domain.utils.FlowUtils.makeDataStateCall
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetIconsUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(): Flow<DataState<Unit>> = makeDataStateCall {
        repository.resetIcons()
    }
}
