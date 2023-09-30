/*
 * CustomIcon.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 21/9/23 11:32
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.model.iconpacks

import android.graphics.drawable.Drawable

data class CustomIcon(
    val drawable: Drawable,
    private val packageName: String,
    private val drawableName: String
) {
    val iconName: String get() = getName(packageName, drawableName)

    companion object {
        fun getName(packageName: String, drawableName: String): String {
            return "${packageName}_${drawableName}"
        }
    }
}
