/*
 * IconPackManager.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 22/9/23 14:19
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.iconpacks.manager.iconpack

import android.content.Context
import android.graphics.drawable.Drawable
import com.ianpedraza.autolauncher.domain.model.applications.AppModel

interface IconPackManager {
    fun loadIconPack(context: Context, packageName: String?)
    fun getIcon(context: Context, app: AppModel): Drawable?
}
