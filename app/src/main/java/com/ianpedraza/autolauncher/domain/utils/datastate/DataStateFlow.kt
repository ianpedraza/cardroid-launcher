/*
 * DataStateFlow.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 25/9/23 14:03
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.utils.datastate

import kotlinx.coroutines.flow.Flow

typealias DataStateFlow<T> = Flow<DataState<T>>
