/*
 * Shapes.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 27/9/23 19:52
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.cornerRadiusExtraLarge
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.cornerRadiusLarge
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.cornerRadiusSize
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.cornerRadiusSizeExtraSmall
import com.cardroidlauncher.app.presentation.main.utils.StandardDimensions.cornerRadiusSizeSmall

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(cornerRadiusSizeExtraSmall),
    small = RoundedCornerShape(cornerRadiusSizeSmall),
    medium = RoundedCornerShape(cornerRadiusSize),
    large = RoundedCornerShape(cornerRadiusLarge),
    extraLarge = RoundedCornerShape(cornerRadiusExtraLarge),
)
