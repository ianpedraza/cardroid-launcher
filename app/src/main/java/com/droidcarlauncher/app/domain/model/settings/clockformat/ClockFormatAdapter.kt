/*
 * ClockFormatAdapter.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 16/9/23 15:35
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.model.settings.clockformat

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class ClockFormatAdapter : JsonSerializer<ClockFormat>, JsonDeserializer<ClockFormat> {

    override fun serialize(
        format: ClockFormat?,
        type: Type?,
        context: JsonSerializationContext?,
    ): JsonElement = JsonPrimitive(format?.value ?: ClockFormat.default.value)

    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?,
    ): ClockFormat {
        val value = json?.toString()?.toInt() ?: ClockFormat.default.value
        return ClockFormat.fromValue(value)
    }
}
