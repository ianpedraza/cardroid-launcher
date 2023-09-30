/*
 * ScreenOrientationAdapter.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 12:28
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.general.orientation

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class ScreenOrientationAdapter :
    JsonSerializer<ScreenOrientation>,
    JsonDeserializer<ScreenOrientation> {

    override fun serialize(
        orientation: ScreenOrientation?,
        type: Type?,
        context: JsonSerializationContext?,
    ): JsonElement = JsonPrimitive(orientation?.value ?: ScreenOrientation.default.value)

    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?,
    ): ScreenOrientation {
        val value = json?.toString()?.toInt() ?: ScreenOrientation.default.value
        return ScreenOrientation.fromValue(value)
    }
}
