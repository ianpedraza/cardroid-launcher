/*
 * ApplicationsSettingsScreen.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 18/9/23 19:24
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.settings.ui.screens.applications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SelectAll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.utils.datastate.DataState
import com.cardroidlauncher.app.presentation.main.navigation.NavigationComponent
import com.cardroidlauncher.app.presentation.main.ui.components.ProgressIndicator
import com.cardroidlauncher.app.presentation.main.ui.components.icons.AppIconButton
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.LinkError
import com.cardroidlauncher.app.presentation.main.ui.components.linkerror.RetryOptions
import com.cardroidlauncher.app.presentation.main.ui.components.listtile.ListTileApplications
import com.cardroidlauncher.app.presentation.main.ui.components.topbar.AppTopBarBack
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.mediumSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationsSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: ApplicationsSettingsViewModel = hiltViewModel(),
    navigationComponent: NavigationComponent
) {
    val goBack by viewModel.goBack.collectAsState()
    val isAllHidden by viewModel.isAllHidden.collectAsState()
    val appsListState by viewModel.appsListState.collectAsState()

    if (goBack) {
        navigationComponent.back()
        viewModel.onBackPerformed()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBarBack(
                title = R.string.applications,
                onBackPressed = viewModel::onBackPressed,
                actions = {
                    if (appsListState is DataState.Success) {
                        AppIconButton(
                            imageVector = Icons.Outlined.SelectAll,
                            contentDescription = stringResource(R.string.select_all),
                            onClick = viewModel::toggleAll,
                            tint = if (isAllHidden) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            when (appsListState) {
                is DataState.Error -> {
                    LinkError(
                        modifier = Modifier.fillMaxSize(),
                        message = stringResource(R.string.error_getting_system_apps),
                        retryOptions = RetryOptions(
                            buttonText = stringResource(R.string.retry),
                            onButtonClick = viewModel::fetchApplications,
                        ),
                    )
                }

                DataState.Loading -> {
                    ProgressIndicator(Modifier.fillMaxSize())
                }

                is DataState.Success -> {
                    val data =
                        (appsListState as DataState.Success<List<AppModel>>).data.sortedBy { it.label }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .fillMaxWidth()
                                    .padding(mediumSize)
                            ) {
                                Text(
                                    text = stringResource(R.string.show_hide_apps_instructions),
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                )
                            }
                        }

                        items(data) { app ->
                            ListTileApplications(
                                app = app,
                                isChecked = app.hidden.not(),
                                onClick = viewModel::toggleHide
                            )
                        }
                    }
                }
            }


        }
    }
}
