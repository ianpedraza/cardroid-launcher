/*
 * GetIconPackUseCase.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 18:37
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.usecases.iconpacks

import com.droidcarlauncher.app.data.repository.iconpacks.IconPacksRepository
import com.droidcarlauncher.app.domain.model.iconpacks.IconPackModel
import com.droidcarlauncher.app.domain.utils.FlowUtils
import com.droidcarlauncher.app.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIconPackUseCase @Inject constructor(
    private val repository: IconPacksRepository
) {
    suspend operator fun invoke(packageName: String): Flow<DataState<IconPackModel?>> =
        FlowUtils.makeDataStateCall {
            repository.getIconPack(packageName)
        }
}
