/*
 * ApplicationEntity.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 6/9/23 18:20
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.framework.applications.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class AppEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val label: String,
    val packageName: String,
    val activityName: String,
    val position: Int,
    val hidden: Boolean,
    val isSystemApp: Boolean,
    val customLabel: String?,
    val customIconName: String?,
)
