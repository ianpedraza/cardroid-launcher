/*
 * StandbyHelper.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 15:54
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.helper.standby

interface StandbyHelper {
    suspend fun waitStandby(time: Long)
}
