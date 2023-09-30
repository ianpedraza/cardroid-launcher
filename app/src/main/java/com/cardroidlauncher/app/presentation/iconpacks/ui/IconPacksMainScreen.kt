/*
 * IconPacksMainScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 15:44
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.iconpacks.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.presentation.iconpacks.navigation.IconPacksNavigationGraph

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
