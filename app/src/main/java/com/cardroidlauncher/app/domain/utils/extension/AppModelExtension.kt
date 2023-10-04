/*
 * AppModelExtension.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 25/9/23 14:02
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.utils.extension

import com.cardroidlauncher.app.domain.model.applications.AppModel

object AppModelExtension {

    fun List<AppModel>.indexOrNull(item: AppModel): Int? {
        val foundIndex = this.indexOfFirst { it.id == item.id }
        return if (foundIndex >= 0) foundIndex else null
    }

    fun List<AppModel>.move(originIndex: Int, targetIndex: Int): List<AppModel> {
        return this.toMutableList().apply {
            add(targetIndex, removeAt(originIndex))
        }.reindex()
    }

    fun List<AppModel>.reindex(): List<AppModel> {
        return mapIndexed { index, app ->
            app.copy(position = index)
        }
    }
}
