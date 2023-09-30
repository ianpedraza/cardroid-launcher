/*
 * ResetIconsUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 27/9/23 12:16
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.applications

import com.droidcarlauncher.app.data.repository.applications.ApplicationsRepository
import com.droidcarlauncher.app.domain.utils.FlowUtils.makeDataStateCall
import com.droidcarlauncher.app.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetIconsUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(): Flow<DataState<Unit>> = makeDataStateCall {
        repository.resetIcons()
    }
}
