/*
 * IconPacksScreen.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 25/9/23 14:17
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.iconpacks.ui.screens.iconpacks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.iconpacks.navigation.IconPacksNavigationComponent
import com.droidcarlauncher.app.presentation.iconpacks.ui.components.IconPacksList
import com.droidcarlauncher.app.presentation.iconpacks.ui.components.suggestedIcons
import com.droidcarlauncher.app.presentation.main.ui.components.PlayStoreTile
import com.droidcarlauncher.app.presentation.main.ui.components.fullLineColumn
import com.droidcarlauncher.app.presentation.main.ui.components.fullLineVerticalSpacer
import com.droidcarlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.appIconSizeSmall
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.smallSize
import com.droidcarlauncher.app.presentation.main.utils.Utils.launchUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconPacksScreen(
    modifier: Modifier = Modifier,
    app: AppModel? = null,
    viewModel: IconPacksViewModel = hiltViewModel(),
    navigationComponent: IconPacksNavigationComponent
) {
    val goBack by viewModel.goBack.collectAsState()
    val iconPacksState by viewModel.iconPacksState.collectAsState()
    val suggestedIconsState by viewModel.suggestedIconsState.collectAsState()
    val launchPlayStoreUrl by viewModel.launchPlayStoreUrl.collectAsState()
    val iconPackToLaunch by viewModel.iconPackToLaunch.collectAsState()
    val iconSelected by viewModel.iconSelected.collectAsState()
    val canRetryGetSuggestions by viewModel.canRetryGetSuggestions.collectAsState()

    val queryIconPacks = stringResource(R.string.icon_packs)

    LaunchedEffect(Unit) {
        viewModel.setCurrentApp(app)
    }

    if (goBack) {
        navigationComponent.finish(LocalContext.current)
        viewModel.onBackPerformed()
    }

    if (launchPlayStoreUrl != null) {
        LocalContext.current.launchUrl(launchPlayStoreUrl!!)
        viewModel.onLaunchedPlayStore()
    }

    if (iconPackToLaunch != null) {
        navigationComponent.goToIconPack(iconPackToLaunch!!)
        viewModel.onLaunchedIconPack()
    }

    if (iconSelected != null) {
        navigationComponent.finish(LocalContext.current, iconSelected)
        viewModel.onIconSelectedPerformed()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.select_icon,
                onBackPressed = viewModel::onBackPressed,
                icon = Icons.Outlined.Close,
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            LazyVerticalGrid(
                modifier = Modifier.padding(top = smallSize),
                columns = GridCells.Adaptive(appIconSizeSmall),
            ) {
                suggestedIcons(
                    suggestedIconsState = suggestedIconsState,
                    onClick = viewModel::onIconSelected,
                    onRetry = viewModel::retryGetSuggestions,
                    canRetryGetSuggestions = canRetryGetSuggestions
                )

                fullLineVerticalSpacer()

                fullLineColumn {
                    IconPacksList(
                        iconPacksState = iconPacksState,
                        onRetry = viewModel::getIconPacks,
                        onClick = viewModel::launchIconPack
                    )
                    PlayStoreTile(onClick = { viewModel.launchPlayStore(queryIconPacks) })
                }
            }
        }
    }
}


