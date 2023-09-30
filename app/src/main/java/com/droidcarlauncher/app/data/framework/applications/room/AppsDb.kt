/*
 * AppsDb.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 19:19
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.applications.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AppEntity::class],
    version = 1,
)
abstract class AppsDb : RoomDatabase() {
    abstract fun appsDao(): AppsDao
}
