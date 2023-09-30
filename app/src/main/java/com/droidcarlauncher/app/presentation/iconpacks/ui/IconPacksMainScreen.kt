/*
 * IconPacksMainScreen.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 20/9/23 15:44
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.iconpacks.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.iconpacks.navigation.IconPacksNavigationGraph

@Composable
fun IconPacksMainScreen(
    modifier: Modifier = Modifier,
    app: AppModel? = null
) {
    val navController: NavHostController = rememberNavController()

    Box(modifier) {
        IconPacksNavigationGraph(
            app = app,
            navController = navController
        )
    }
}
