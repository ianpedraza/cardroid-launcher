/*
 * IconsSizeSettingsScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 4/10/23 11:04
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.cardroidlauncher.app.presentation.settings.ui.screens.appearance.iconssize

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.settings.appearance.iconssize.IconsSize
import com.cardroidlauncher.app.presentation.main.navigation.NavigationComponent
import com.cardroidlauncher.app.presentation.main.ui.components.listtile.ListTileRadio
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack

@Composable
fun IconsSizeSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: IconsSizeSettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent
) {
    val goBack by viewModel.goBack.collectAsState()
    val iconsSize by viewModel.iconsSize.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.icons_size,
                onBackPressed = viewModel::onBackPressed,
            )
        },
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(IconsSize.values) { size ->
                    ListTileRadio(
                        text = stringResource(size.label),
                        selected = iconsSize == size,
                        onClick = { viewModel.setIconsSize(size) },
                    )
                }
            }
        }
    }
}
