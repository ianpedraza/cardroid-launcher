/*
 * Modifiers.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 7/9/23 13:39
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.utils

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput

object Modifiers {

    sealed class Gesture {
        object OnDoubleTap : Gesture()
        object OnLongPress : Gesture()
        object OnPress : Gesture()
        object OnTap : Gesture()
    }

    fun Modifier.anyGesture(
        onEvent: (Gesture) -> Unit,
    ): Modifier = composed(
        factory = {
            this.then(
                Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { onEvent(Gesture.OnTap) },
                        onDoubleTap = { onEvent(Gesture.OnDoubleTap) },
                        onLongPress = { onEvent(Gesture.OnLongPress) },
                        onPress = { onEvent(Gesture.OnPress) },
                    )
                },
            )
        },
    )
}
