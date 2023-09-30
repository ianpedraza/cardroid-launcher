/*
 * ResetIconsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 27/9/23 12:16
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.applications

import com.ianpedraza.autolauncher.data.repository.applications.ApplicationsRepository
import com.ianpedraza.autolauncher.domain.utils.FlowUtils.makeDataStateCall
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetIconsUseCase @Inject constructor(
    private val repository: ApplicationsRepository
) {
    suspend operator fun invoke(): Flow<DataState<Unit>> = makeDataStateCall {
        repository.resetIcons()
    }
}
