/*
 * WallpaperAdapter.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 9/9/23 11:43
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.wallpaper

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class WallpaperAdapter : JsonSerializer<Wallpaper>, JsonDeserializer<Wallpaper> {

    override fun serialize(
        wallpaper: Wallpaper?,
        type: Type?,
        context: JsonSerializationContext?,
    ): JsonElement = JsonPrimitive(wallpaper?.value ?: Wallpaper.default.value)

    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?,
    ): Wallpaper {
        val value = json?.toString()?.toInt() ?: Wallpaper.default.value
        return Wallpaper.fromValue(value)
    }
}
