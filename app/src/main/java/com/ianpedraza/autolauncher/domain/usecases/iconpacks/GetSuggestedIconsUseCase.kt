/*
 * GetSuggestedIconsUseCase.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 12:22
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.usecases.iconpacks

import com.ianpedraza.autolauncher.data.repository.iconpacks.IconPacksRepository
import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.utils.FlowUtils.makeDataStateCall
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
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
