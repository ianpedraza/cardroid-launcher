/*
 * DataStateFlow.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 25/9/23 14:03
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.utils.datastate

import kotlinx.coroutines.flow.Flow

typealias DataStateFlow<T> = Flow<DataState<T>>
