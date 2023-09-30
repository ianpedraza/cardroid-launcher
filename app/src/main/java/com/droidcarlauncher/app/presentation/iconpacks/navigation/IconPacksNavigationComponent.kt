/*
 * IconPacksNavigationComponent.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 15:53
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.iconpacks.navigation

import android.content.Context
import com.droidcarlauncher.app.domain.model.iconpacks.CustomIcon
import com.droidcarlauncher.app.domain.model.iconpacks.IconPackModel

interface IconPacksNavigationComponent {
    fun goToIconPack(iconPack: IconPackModel)
    fun finish(context: Context, result: CustomIcon? = null)
    fun back()
}
