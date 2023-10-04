/*
 * IconPackScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 25/9/23 14:17
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.iconpacks.ui.screens.iconpack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.utils.Constants.EMPTY_STRING
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import com.cardroidlauncher.app.presentation.iconpacks.navigation.IconPacksNavigationComponent
import com.cardroidlauncher.app.presentation.iconpacks.ui.components.IconsList
import com.cardroidlauncher.app.presentation.main.ui.components.ProgressIndicator
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.LinkError
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.RetryOptions
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconPackScreen(
    modifier: Modifier = Modifier,
    viewModel: IconPackViewModel = hiltViewModel(),
    navigationComponent: IconPacksNavigationComponent,
) {
    val goBack by viewModel.goBack.collectAsState()
    val iconPack by viewModel.iconPack.collectAsState()
    val iconsState by viewModel.iconsState.collectAsState()
    val iconSelected by viewModel.iconSelected.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    if (iconSelected != null) {
        navigationComponent.finish(LocalContext.current, iconSelected)
        viewModel.onIconSelectedPerformed()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarSearch(
                title = iconPack?.label ?: EMPTY_STRING,
                onBackPressed = viewModel::onBackPressed,
                isSearchEnabled = iconsState is DataState.Success,
                onValueChange = viewModel::search
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            iconPack?.let {
                when (iconsState) {
                    is DataState.Error -> {
                        LinkError(
                            modifier = Modifier.fillMaxSize(),
                            message = stringResource(R.string.error_getting_icons),
                            retryOptions = RetryOptions(
                                buttonText = stringResource(R.string.retry),
                                onButtonClick = viewModel::getAllIcons,
                            ),
                        )
                    }

                    DataState.Loading -> {
                        ProgressIndicator(Modifier.fillMaxSize())
                    }

                    is DataState.Success -> {
                        val data = (iconsState as DataState.Success<List<CustomIcon>>).data
                        IconsList(
                            modifier = Modifier.fillMaxSize(),
                            icons = data,
                            onClick = viewModel::onIconSelected
                        )
                    }
                }
            }
        }
    }

}

