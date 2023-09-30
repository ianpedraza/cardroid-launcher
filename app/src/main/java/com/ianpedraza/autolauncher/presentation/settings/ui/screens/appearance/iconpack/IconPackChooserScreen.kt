/*
 * IconPackChooserScreen.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 22/9/23 12:49
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.settings.ui.screens.appearance.iconpack

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import com.ianpedraza.autolauncher.presentation.main.navigation.NavigationComponent
import com.ianpedraza.autolauncher.presentation.main.ui.components.IconPackItem
import com.ianpedraza.autolauncher.presentation.main.ui.components.PlayStoreTile
import com.ianpedraza.autolauncher.presentation.main.ui.components.ProgressIndicator
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.LinkError
import com.ianpedraza.autolauncher.presentation.main.ui.components.linkerror.RetryOptions
import com.ianpedraza.autolauncher.presentation.main.ui.components.listtile.ListTileRadio
import com.ianpedraza.autolauncher.presentation.main.ui.components.topbar.AppTopBarBack
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions
import com.ianpedraza.autolauncher.presentation.main.utils.Utils.launchUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconPackChooserScreen(
    modifier: Modifier = Modifier,
    viewModel: IconPackChooserViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent
) {
    val goBack by viewModel.goBack.collectAsState()
    val iconPacksState by viewModel.iconPacksState.collectAsState()
    val launchPlayStore by viewModel.launchPlayStore.collectAsState()
    val selectedIconPackPackageName by viewModel.selectedIconPackPackageName.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    if (launchPlayStore) {
        val query = stringResource(R.string.play_store_query_icon_packs)
        LocalContext.current.launchUrl(query)
        viewModel.onLaunchedPlayStore()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.icon_pack,
                onBackPressed = viewModel::onBackPressed,
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                ListTileRadio(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.none),
                    selected = selectedIconPackPackageName == null,
                    onClick = { viewModel.selectIconPack(null) }
                )
            }

            when (iconPacksState) {
                is DataState.Error -> {
                    item {
                        LinkError(
                            modifier = Modifier
                                .fillMaxSize()
                                .heightIn(min = StandardDimensions.textErrorHeight),
                            message = stringResource(R.string.error_getting_icon_packs),
                            retryOptions = RetryOptions(
                                buttonText = stringResource(R.string.retry),
                                onButtonClick = viewModel::fetchIconPacks,
                            ),
                        )
                    }
                }

                DataState.Loading -> {
                    item {
                        ProgressIndicator(
                            Modifier
                                .fillMaxSize()
                                .heightIn(min = StandardDimensions.textErrorHeight)
                        )
                    }
                }

                is DataState.Success -> {
                    val iconPacks = (iconPacksState as DataState.Success<List<IconPackModel>>).data
                    items(iconPacks) { iconPack ->
                        ListTileRadio(
                            content = {
                                IconPackItem(
                                    iconPack = iconPack,
                                    onClick = { viewModel.selectIconPack(iconPack) }
                                )
                            },
                            onClick = { viewModel.selectIconPack(iconPack) },
                            selected = selectedIconPackPackageName == iconPack.packageName
                        )
                    }
                }
            }


            item {
                PlayStoreTile(onClick = viewModel::launchPlayStore)
            }
        }
    }
}
