/*
 * AppsActionsBar.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 17/9/23 11:35
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.applications.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.HideSource
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import com.droidcarlauncher.app.R
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.applications.ui.LauncherEvent
import com.droidcarlauncher.app.presentation.applications.ui.components.drag.DropContainer
import com.droidcarlauncher.app.presentation.applications.ui.components.drag.OnDragging
import com.droidcarlauncher.app.presentation.main.ui.components.icons.AppIcon
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.appsActionsBarHeight
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.extraSmallSize
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.iconSizeSmall
import com.droidcarlauncher.app.presentation.main.utils.StandardDimensions.mediumBorder

@Composable
fun AppsActionsBar(
    modifier: Modifier = Modifier,
    onEvent: (LauncherEvent) -> Unit,
) {
    OnDragging(
        modifier = modifier.fillMaxWidth(),
    ) { dragging, data ->
        AnimatedVisibility(
            visible = dragging,
            enter = expandIn(
                expandFrom = Alignment.TopCenter,
                initialSize = { IntSize(it.width, 0) },
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh,
                    visibilityThreshold = IntSize.VisibilityThreshold,
                ),
            ),
            exit = shrinkOut(
                shrinkTowards = Alignment.TopCenter,
                targetSize = { IntSize(it.width, 0) },
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium,
                    visibilityThreshold = IntSize.VisibilityThreshold,
                ),
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(appsActionsBarHeight),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                AppsActionButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.info),
                    icon = Icons.Outlined.Info,
                    onItemDragged = {
                        onEvent(LauncherEvent.OnLaunchAppInfo(it))
                    },
                )

                AppsActionButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.edit),
                    icon = Icons.Outlined.Edit,
                    onItemDragged = {
                        onEvent(LauncherEvent.OnEditApp(it))
                    },
                )

                AppsActionButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.hide),
                    icon = Icons.Outlined.HideSource,
                    onItemDragged = {
                        onEvent(LauncherEvent.OnHideApp(it))
                    },
                )

                if (data?.isSystemApp == false) {
                    AppsActionButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.uninstall),
                        icon = Icons.Outlined.Delete,
                        onItemDragged = {
                            onEvent(LauncherEvent.OnUninstallApp(it))
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun AppsActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onItemDragged: (AppModel) -> Unit,
    icon: ImageVector,
) {
    DropContainer(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .padding(extraSmallSize)
        ),
        onItemDragged = onItemDragged,
    ) { isCurrentDropTarget ->

        val backgroundColor = if (isCurrentDropTarget) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surface
        }

        val contentColor = MaterialTheme.colorScheme.onSurface

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(backgroundColor.copy(alpha = 0.2f))
                .border(
                    width = mediumBorder,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
                .align(Alignment.Center),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                AppIcon(
                    imageVector = icon,
                    contentDescription = "${stringResource(R.string.icon)} $text",
                    tint = contentColor,
                    size = iconSizeSmall,
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
