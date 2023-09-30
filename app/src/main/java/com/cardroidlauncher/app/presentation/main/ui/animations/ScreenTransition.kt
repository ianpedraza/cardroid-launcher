/*
 * ScreenTransition.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 21/8/23 17:27
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.presentation.main.ui.animations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

abstract class ScreenTransition {
    companion object {
        private const val SCREEN_TRANSITION_DURATION = 300
    }

    sealed class Defaults {
        object None : ScreenTransition() {
            override val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
                { null }
            override val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
                { null }
            override val popEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
                { null }
            override val popExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
                { null }
        }

        object UpDown : ScreenTransition() {
            override val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
                {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }

            override val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
                {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }

            override val popEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
                {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }

            override val popExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
                {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }
        }

        object StartToEnd : ScreenTransition() {
            override val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
                {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }

            override val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
                {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }

            override val popEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
                {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }

            override val popExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
                {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(SCREEN_TRANSITION_DURATION),
                    )
                }
        }
    }

    abstract val enter: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)
    abstract val exit: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)
    abstract val popEnter: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)
    abstract val popExit: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)
}
