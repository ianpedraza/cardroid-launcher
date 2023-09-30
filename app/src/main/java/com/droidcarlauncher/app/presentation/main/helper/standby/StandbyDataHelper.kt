/*
 * StandbyDataHelper.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 28/9/23 14:59
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.helper.standby

import com.droidcarlauncher.app.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StandbyDataHelper @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : StandbyHelper {

    override suspend fun waitStandby(time: Long) = withContext(dispatcher) {
        delay(time)
    }
}
