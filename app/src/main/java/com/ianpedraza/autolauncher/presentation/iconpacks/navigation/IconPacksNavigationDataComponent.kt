/*
 * IconPacksNavigationDataComponent.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 20/9/23 15:53
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel
import com.ianpedraza.autolauncher.presentation.iconpacks.ui.IconPacksActivity

class IconPacksNavigationDataComponent(
    private val navController: NavController,
    private val gson: Gson,
) : IconPacksNavigationComponent {

    override fun goToIconPack(iconPack: IconPackModel) {
        val json = gson.toJson(iconPack)
        val route = IconPacksRoutes.IconPackScreen.createRoute(json)
        navController.navigate(route)
    }

    override fun finish(context: Context, customIcon: CustomIcon?) {
        (context as? Activity)?.let {
            with(it) {
                if (customIcon != null) {
                    val data = Intent().apply {
                        putExtra(IconPacksActivity.PARAM_CUSTOM_ICON_NAME, customIcon.iconName)
                    }
                    setResult(Activity.RESULT_OK, data)
                } else {
                    setResult(Activity.RESULT_CANCELED)
                }
                finish()
            }
        }
    }

    override fun back() {
        navController.popBackStack()
    }
}
