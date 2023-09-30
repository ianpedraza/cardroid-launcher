/*
 * StandbyHelper.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 15:54
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.main.helper.standby

interface StandbyHelper {
    suspend fun waitStandby(time: Long)
}
