/*
 * DataTypesExtensions.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 11/9/23 12:49
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.utils.extension

import com.cardroidlauncher.app.domain.utils.Constants.EMPTY_STRING

object DataTypesExtensions {

    @JvmOverloads
    fun Boolean?.value(default: Boolean = false) = this ?: default

    @JvmOverloads
    fun Int?.value(default: Int = 0) = this ?: default

    @JvmOverloads
    fun Float?.value(default: Float = 0f) = this ?: default

    @JvmOverloads
    fun Double?.value(default: Double = 0.0) = this ?: default

    @JvmOverloads
    fun Long?.value(default: Long = 0L) = this ?: default

    @JvmOverloads
    fun String?.value(default: String = EMPTY_STRING) = this ?: default
}
