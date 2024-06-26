/*
 * Annotations.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 8/9/23 14:40
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SettingsGson

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SimpleGson
