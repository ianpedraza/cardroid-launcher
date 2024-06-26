/*
 * IconPackDataHelper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 22/9/23 15:03
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.iconpack.helper

import android.content.Context
import com.cardroidlauncher.app.data.framework.iconpacks.manager.iconpack.IconPackDataManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IconPackDataHelper @Inject constructor(
    @ApplicationContext private val context: Context
) : IconPackHelper {

    override fun loadIconPack(packageName: String?) {
        IconPackDataManager.loadIconPack(context, packageName)
    }
}
