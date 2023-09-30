/*
 * DarkThemeModeAdapter.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 22/9/23 11:43
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.model.settings.appearance.darkheme

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class DarkThemeModeAdapter : JsonSerializer<DarkThemeMode>, JsonDeserializer<DarkThemeMode> {

    override fun serialize(
        darkThemeMode: DarkThemeMode?,
        type: Type?,
        context: JsonSerializationContext?,
    ): JsonElement = JsonPrimitive(darkThemeMode?.value ?: DarkThemeMode.default.value)

    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?,
    ): DarkThemeMode {
        val value = json?.toString()?.toInt() ?: DarkThemeMode.default.value
        return DarkThemeMode.fromValue(value)
    }
}
