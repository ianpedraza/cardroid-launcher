/*
 * MainScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 18:43
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cardroidlauncher.app.presentation.main.navigation.AppNavigationGraph

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()

    Box(modifier) {
        AppNavigationGraph(
            navController = navController,
        )
    }
}
