/*
 * StandbyModeTimeAdapter.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 9/9/23 11:43
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.model.settings.standby

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class StandbyModeAdapter : JsonSerializer<StandbyMode>, JsonDeserializer<StandbyMode> {

    override fun serialize(
        standbyMode: StandbyMode?,
        type: Type?,
        context: JsonSerializationContext?,
    ): JsonElement = JsonPrimitive(standbyMode?.value ?: StandbyMode.default.value)

    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?,
    ): StandbyMode {
        val value = json?.toString()?.toLong() ?: StandbyMode.default.value
        return StandbyMode.fromValue(value)
    }
}
