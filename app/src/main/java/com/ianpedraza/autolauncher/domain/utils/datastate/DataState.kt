/*
 * DataState.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 25/9/23 14:03
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.utils.datastate

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()

    data class Error(val throwable: Throwable) : DataState<Nothing>()

    object Loading : DataState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success [data=$data]"
            is Error -> "Error [exception=$throwable]"
            Loading -> "Loading"
        }
    }

    val isSuccess: Boolean get() = this is Success
}
