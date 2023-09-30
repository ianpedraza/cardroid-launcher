/*
 * AppsDb.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 8/9/23 19:19
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.data.framework.applications.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AppEntity::class],
    version = 1,
)
abstract class AppsDb : RoomDatabase() {
    abstract fun appsDao(): AppsDao
}
