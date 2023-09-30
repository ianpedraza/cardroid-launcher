/*
 * FetchIconPacksUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 12:20
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.iconpacks

import com.droidcarlauncher.app.data.repository.iconpacks.IconPacksRepository
import com.droidcarlauncher.app.domain.model.iconpacks.IconPackModel
import com.droidcarlauncher.app.domain.utils.FlowUtils.makeDataStateCall
import com.droidcarlauncher.app.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchIconPacksUseCase @Inject constructor(
    private val repository: IconPacksRepository
) {
    suspend operator fun invoke(): Flow<DataState<List<IconPackModel>>> = makeDataStateCall {
        repository.fetchIconPacks()
    }
}
