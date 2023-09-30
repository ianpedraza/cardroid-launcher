/*
 * GetSuggestedIconsUseCase.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 12:22
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.usecases.iconpacks

import com.cardroidlauncher.app.data.repository.iconpacks.IconPacksRepository
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.utils.FlowUtils.makeDataStateCall
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSuggestedIconsUseCase @Inject constructor(
    private val repository: IconPacksRepository
) {

    suspend operator fun invoke(app: AppModel): Flow<DataState<List<CustomIcon>>> =
        makeDataStateCall {
            repository.getSuggestedIcons(app)
        }

}
