/*
 * GetIconPackUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 18:37
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.iconpacks

import com.cardroidlauncher.app.data.repository.iconpacks.IconPacksRepository
import com.cardroidlauncher.app.domain.model.iconpacks.IconPackModel
import com.cardroidlauncher.app.domain.utils.FlowUtils
import com.cardroidlauncher.app.domain.utils.datastate.DataState
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
