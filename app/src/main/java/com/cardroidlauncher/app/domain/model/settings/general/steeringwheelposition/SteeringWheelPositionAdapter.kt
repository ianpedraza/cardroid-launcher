/*
 * SteeringWheelPositionAdapter.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 16/9/23 14:40
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.general.steeringwheelposition

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class SteeringWheelPositionAdapter :
    JsonSerializer<SteeringWheelPosition>,
    JsonDeserializer<SteeringWheelPosition> {

    override fun serialize(
        position: SteeringWheelPosition?,
        type: Type?,
        context: JsonSerializationContext?,
    ): JsonElement = JsonPrimitive(position?.value ?: SteeringWheelPosition.default.value)

    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?,
    ): SteeringWheelPosition {
        val value = json?.toString()?.toInt() ?: SteeringWheelPosition.default.value
        return SteeringWheelPosition.fromValue(value)
    }
}
