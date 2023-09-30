/*
 * EditAppDialog.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 19/9/23 11:08
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.applications.ui.components

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.cardroidlauncher.app.R
import com.cardroidlauncher.app.data.framework.iconpacks.manager.cache.CustomIconsDataCache
import com.cardroidlauncher.app.domain.model.applications.AppModel
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.utils.extension.DataTypesExtensions.value
import com.cardroidlauncher.app.presentation.applications.helper.validator.CustomAppsValidator
import com.cardroidlauncher.app.presentation.applications.ui.LauncherEvent
import com.cardroidlauncher.app.presentation.iconpacks.ui.IconPacksActivity
import com.cardroidlauncher.app.presentation.main.ui.components.DrawableAppIcon
import com.cardroidlauncher.app.presentation.main.ui.components.VerticalSpacer
import com.cardroidlauncher.app.presentation.main.ui.components.icons.AppIconButton
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.appIconSizeSmall
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.iconElevation
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.iconSizeSmall
import com.cardroidlauncher.app.presentation.main.utils.Utils.getCustomIcon
import com.cardroidlauncher.app.presentation.main.utils.Utils.getStockIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAppDialog(
    modifier: Modifier = Modifier,
    app: AppModel,
    onEvent: (LauncherEvent) -> Unit,
    @StringRes error: Int? = null
) {
    val context = LocalContext.current

    var icon by remember { mutableStateOf<Drawable?>(null) }
    var editedApp by remember { mutableStateOf(app) }
    var customIcon by remember { mutableStateOf<CustomIcon?>(null) }

    val chooseIconLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                (result.data?.getStringExtra(IconPacksActivity.PARAM_CUSTOM_ICON_NAME))?.let { iconName ->
                    CustomIconsDataCache.getCached(iconName)?.let {
                        customIcon = it
                        icon = it.drawable
                        editedApp = editedApp.copy(customIconName = iconName)
                    }
                }
            }
        }

    LaunchedEffect(app.customIconName) {
        icon = if (app.customIconName != null) {
            context.getCustomIcon(app.customIconName)
        } else {
            context.getStockIcon(app)
        }
    }

    AlertDialog(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onDismissRequest = { onEvent(LauncherEvent.OnDismissEditApp) },
        confirmButton = {
            Button(
                onClick = { onEvent(LauncherEvent.OnSaveEditedApp(editedApp, customIcon)) },
                enabled = error == null
            ) {
                Text(text = stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(LauncherEvent.OnDismissEditApp) }) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                AppIconButton(
                    imageVector = Icons.Outlined.Restore,
                    size = iconSizeSmall,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = stringResource(R.string.restore),
                    onClick = {
                        editedApp = editedApp.copy(
                            customLabel = null,
                            customIconName = null,
                        )
                        icon = context.getStockIcon(app)
                        customIcon = null
                        onEvent(LauncherEvent.OnChangeEditApp(editedApp))
                    },
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DrawableAppIcon(
                        modifier = modifier.then(Modifier.fillMaxSize()),
                        drawable = icon,
                        contentDescription = app.label,
                        size = appIconSizeSmall,
                        elevation = iconElevation,
                        onClick = {
                            val intent = IconPacksActivity.getIntent(context, app)
                            chooseIconLauncher.launch(intent)
                        }
                    )
                    VerticalSpacer()
                    TextField(
                        value = editedApp.customLabel ?: editedApp.label,
                        onValueChange = {
                            val isLastWhitespace =
                                editedApp.customLabel
                                    ?.isNotEmpty().value() && editedApp.customLabel?.last()
                                    ?.isWhitespace().value()

                            val isDoubleWhitespace =
                                it.isNotEmpty() && it.last().isWhitespace() && isLastWhitespace

                            if (it.length < CustomAppsValidator.MAX_LENGTH && !isDoubleWhitespace) {
                                editedApp = editedApp.copy(customLabel = it)
                                onEvent(LauncherEvent.OnChangeEditApp(editedApp))
                            }
                        },
                        isError = error != null,
                        supportingText = {
                            error?.let { resource ->
                                Text(
                                    text = stringResource(resource),
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        maxLines = 1,
                        singleLine = true
                    )
                }
            }
        }
    )
}
