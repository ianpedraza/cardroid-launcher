/*
 * IconPackManager.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 22/9/23 14:19
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.iconpacks.manager.iconpack

import android.content.Context
import android.graphics.drawable.Drawable
import com.droidcarlauncher.app.domain.model.applications.AppModel

interface IconPackManager {
    fun loadIconPack(context: Context, packageName: String?)
    fun getIcon(context: Context, app: AppModel): Drawable?
}
