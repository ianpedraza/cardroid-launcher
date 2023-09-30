/*
 * StandbyDataHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 28/9/23 14:59
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.helper.standby

import com.cardroidlauncher.app.di.DefaultDispatcher
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
