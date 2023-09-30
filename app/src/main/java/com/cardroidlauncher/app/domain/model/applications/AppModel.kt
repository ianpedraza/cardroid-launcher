/*
 * AppModel.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 19:02
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.applications

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppModel(
    val id: Long,
    val label: String,
    val packageName: String,
    val activityName: String,
    val position: Int = UNDEFINED_POSITION,
    val hidden: Boolean = false,
    val isSystemApp: Boolean = true,
    val customLabel: String? = null,
    val customIconName: String? = null,
) : Parcelable {
    companion object {
        const val UNDEFINED_POSITION = -1
    }
}
