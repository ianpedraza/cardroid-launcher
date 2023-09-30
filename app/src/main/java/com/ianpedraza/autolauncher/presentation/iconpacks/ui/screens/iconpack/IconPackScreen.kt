/*
 * IconPackScreen.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 25/9/23 14:17
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.iconpacks.ui.screens.iconpack

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
import com.ianpedraza.autolauncher.R
import com.ianpedraza.autolauncher.domain.model.iconpacks.CustomIcon
import com.ianpedraza.autolauncher.domain.utils.Constants.EMPTY_STRING
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import com.ianpedraza.autolauncher.presentation.iconpacks.navigation.IconPacksNavigationComponent
import com.ianpedraza.autolauncher.presentation.iconpacks.ui.components.IconsList
import com.ianpedraza.autolauncher.presentation.main.ui.components.ProgressIndicator
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.LinkError
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.RetryOptions
import com.ianpedraza.autolauncher.presentation.main.ui.components.topbar.AppTopBarBack

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
            AppTopBarBack(
                title = iconPack?.label ?: EMPTY_STRING,
                onBackPressed = viewModel::onBackPressed,
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

