/*
 * StandbyDataHelper.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 28/9/23 14:59
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.helper.standby

import com.ianpedraza.autolauncher.di.DefaultDispatcher
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
