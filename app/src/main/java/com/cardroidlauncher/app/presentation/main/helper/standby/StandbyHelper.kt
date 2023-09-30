/*
 * StandbyHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 15:54
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.helper.standby

interface StandbyHelper {
    suspend fun waitStandby(time: Long)
}
