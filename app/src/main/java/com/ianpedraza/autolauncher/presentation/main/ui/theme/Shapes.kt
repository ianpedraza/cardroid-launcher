/*
 * Shapes.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 27/9/23 19:52
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.presentation.main.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.cornerRadiusExtraLarge
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.cornerRadiusLarge
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.cornerRadiusSize
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.cornerRadiusSizeExtraSmall
import com.ianpedraza.autolauncher.presentation.main.utils.StandardDimensions.cornerRadiusSizeSmall

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(cornerRadiusSizeExtraSmall),
    small = RoundedCornerShape(cornerRadiusSizeSmall),
    medium = RoundedCornerShape(cornerRadiusSize),
    large = RoundedCornerShape(cornerRadiusLarge),
    extraLarge = RoundedCornerShape(cornerRadiusExtraLarge),
)
