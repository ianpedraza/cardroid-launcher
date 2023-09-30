/*
 * AppsDao.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 8/9/23 19:19
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.data.framework.applications.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppsDao {
    @Query("SELECT * FROM apps ORDER BY position ASC")
    suspend fun getAll(): List<AppEntity>

    @Query("SELECT * FROM apps ORDER BY position ASC")
    fun observeApps(): Flow<List<AppEntity>>

    @Query("SELECT * FROM apps WHERE id = :id")
    fun getApp(id: Long): AppEntity?

    @Insert
    suspend fun insert(apps: List<AppEntity>)

    @Update
    suspend fun update(apps: List<AppEntity>)

    @Delete
    suspend fun remove(apps: List<AppEntity>)
}
