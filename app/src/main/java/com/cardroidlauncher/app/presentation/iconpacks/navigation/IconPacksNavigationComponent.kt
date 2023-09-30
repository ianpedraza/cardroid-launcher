/*
 * IconPacksNavigationComponent.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 15:53
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.iconpacks.navigation

import android.content.Context
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.model.iconpacks.IconPackModel

interface IconPacksNavigationComponent {
    fun goToIconPack(iconPack: IconPackModel)
    fun finish(context: Context, result: CustomIcon? = null)
    fun back()
}
