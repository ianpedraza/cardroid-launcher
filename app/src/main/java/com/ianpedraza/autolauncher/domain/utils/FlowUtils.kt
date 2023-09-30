/*
 * FlowUtils.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 6/9/23 18:59
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.utils

import com.ianpedraza.autolauncher.domain.model.applications.AppModel
import com.ianpedraza.autolauncher.domain.utils.datastate.DataState
import com.ianpedraza.autolauncher.domain.utils.datastate.DataStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

object FlowUtils {

    fun <T> makeDataStateCall(
        call: suspend () -> T,
    ): Flow<DataState<T>> = flow {
        emit(DataState.Loading)
        try {
            val data = call.invoke()
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun <T> makeDataStateFlow(
        flow: suspend () -> Flow<List<AppModel>>,
    ): DataStateFlow<T> {
        return flow()
            .map { DataState.Success(it) as DataState<T> }
            .onStart { emit(DataState.Loading) }
            .catch { emit(DataState.Error(it)) }
    }
}
