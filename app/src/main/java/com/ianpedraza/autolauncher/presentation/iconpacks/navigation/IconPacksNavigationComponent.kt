/*
 * IconPacksNavigationComponent.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 15:53
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.navigation

import android.content.Context
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel

interface IconPacksNavigationComponent {
    fun goToIconPack(iconPack: IconPackModel)
    fun finish(context: Context, result: CustomIcon? = null)
    fun back()
}
