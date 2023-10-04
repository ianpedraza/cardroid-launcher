/*
 * CustomIcon.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 21/9/23 11:32
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.iconpacks

import android.graphics.drawable.Drawable

data class CustomIcon(
    val drawable: Drawable,
    private val packageName: String,
    val drawableName: String
) {
    val iconName: String get() = getName(packageName, drawableName)

    companion object {
        fun getName(packageName: String, drawableName: String): String {
            return "${packageName}_${drawableName}"
        }
    }
}
