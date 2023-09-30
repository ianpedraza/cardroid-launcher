/*
 * IconPackModel.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 10:44
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.iconpacks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IconPackModel(
    val id: String,
    val packageName: String,
    val label: String,
    val activityName: String
) : Parcelable
