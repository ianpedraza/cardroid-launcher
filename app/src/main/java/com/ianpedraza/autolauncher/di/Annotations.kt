/*
 * Annotations.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 14:40
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.di

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
