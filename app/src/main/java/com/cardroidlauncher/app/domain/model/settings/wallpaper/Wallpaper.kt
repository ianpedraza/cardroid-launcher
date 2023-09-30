/*
 * Wallpaper.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 9/9/23 11:43
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.wallpaper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.cardroidlauncher.app.R

sealed class Wallpaper(
    val value: Int,
    @DrawableRes val night: Int? = null,
    @DrawableRes val day: Int? = null,
    @StringRes val title: Int? = null,
    @StringRes val author: Int? = null,
) {

    object None : Wallpaper(
        value = NONE_VALUE,
    )

    object Blobs : Wallpaper(
        value = BLOBS_VALUE,
        day = R.drawable.blobs_day,
        night = R.drawable.blobs_night,
        title = R.string.blobs_3,
        author = R.string.marcelabdiniz
    )

    object BlobsRed : Wallpaper(
        value = BLOBS_RED_VALUE,
        day = R.drawable.blobs_red_day,
        night = R.drawable.blobs_red_night,
        title = R.string.blobs_2,
        author = R.string.marcelabdiniz
    )

    object CircleGradient : Wallpaper(
        value = CIRCLES_GRADIENT_VALUE,
        day = R.drawable.circle_gradient_day,
        night = R.drawable.circle_gradient_night,
        title = R.string.circle_gradient,
        author = R.string.joshuathe4th
    )

    object Curves : Wallpaper(
        value = CURVES_VALUE,
        day = R.drawable.curves_day,
        night = R.drawable.curves_night,
        title = R.string.modern_curves_philip_oroni,
        author = R.string.monochromaticman
    )

    object Wavey : Wallpaper(
        value = WAVEY_VALUE,
        day = R.drawable.wavey_day,
        night = R.drawable.wavey_night,
        title = R.string.wavey,
        author = R.string.cryterion
    )

    object MakingWavesPurple : Wallpaper(
        value = MAKING_WAVES_PURPLE_VALUE,
        day = R.drawable.making_waves_purple_day,
        night = R.drawable.making_waves_night,
        title = R.string.making_waves,
        author = R.string.bobbyv
    )

    object MakingWavesOrange : Wallpaper(
        value = MAKING_WAVES_ORANGE_VALUE,
        day = R.drawable.making_waves_orange_day,
        night = R.drawable.making_waves_night,
        title = R.string.making_waves,
        author = R.string.bobbyv
    )

    object MakingWavesGreen : Wallpaper(
        value = MAKING_WAVES_GREEN_VALUE,
        day = R.drawable.making_waves_green_day,
        night = R.drawable.making_waves_night,
        title = R.string.making_waves,
        author = R.string.bobbyv
    )

    object MakingWavesBlue : Wallpaper(
        value = MAKING_WAVES_BLUE_VALUE,
        day = R.drawable.making_waves_blue_day,
        night = R.drawable.making_waves_night,
        title = R.string.making_waves,
        author = R.string.bobbyv
    )

    object AbstractWaves : Wallpaper(
        value = ABSTRACT_WAVES_VALUE,
        day = R.drawable.abstract_waves_day,
        night = R.drawable.abstract_waves_night,
        title = R.string.abstract_waves,
        author = R.string.gnugomez
    )

    object Sky : Wallpaper(
        value = SKY_VALUE,
        day = R.drawable.sky_day,
        night = R.drawable.sky_night,
        title = R.string.vibrant_sky,
        author = R.string.bethanycreates
    )

    companion object {

        val default = None

        val values
            get() = Wallpaper::class.sealedSubclasses
                .mapNotNull { it.objectInstance }
                .sortedBy { it.value }

        private const val NONE_VALUE = 0
        private const val BLOBS_VALUE = 1
        private const val BLOBS_RED_VALUE = 2
        private const val CIRCLES_GRADIENT_VALUE = 3
        private const val CURVES_VALUE = 4
        private const val WAVEY_VALUE = 5
        private const val MAKING_WAVES_PURPLE_VALUE = 6
        private const val MAKING_WAVES_ORANGE_VALUE = 7
        private const val MAKING_WAVES_GREEN_VALUE = 8
        private const val MAKING_WAVES_BLUE_VALUE = 9
        private const val ABSTRACT_WAVES_VALUE = 10
        private const val SKY_VALUE = 11

        fun fromValue(value: Int?): Wallpaper {
            return when (value) {
                NONE_VALUE -> None
                BLOBS_VALUE -> Blobs
                BLOBS_RED_VALUE -> BlobsRed
                CIRCLES_GRADIENT_VALUE -> CircleGradient
                CURVES_VALUE -> Curves
                WAVEY_VALUE -> Wavey
                MAKING_WAVES_PURPLE_VALUE -> MakingWavesPurple
                MAKING_WAVES_ORANGE_VALUE -> MakingWavesOrange
                MAKING_WAVES_GREEN_VALUE -> MakingWavesGreen
                MAKING_WAVES_BLUE_VALUE -> MakingWavesBlue
                ABSTRACT_WAVES_VALUE -> AbstractWaves
                SKY_VALUE -> Sky
                else -> default
            }
        }
    }
}
