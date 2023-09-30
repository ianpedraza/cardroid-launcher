/*
 * AppModelExtension.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 25/9/23 14:02
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.utils.extension

import com.ianpedraza.autolauncher.domain.model.applications.AppModel

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

    private fun List<AppModel>.reindex(): List<AppModel> {
        return mapIndexed { index, app ->
            app.copy(position = index)
        }
    }
}
