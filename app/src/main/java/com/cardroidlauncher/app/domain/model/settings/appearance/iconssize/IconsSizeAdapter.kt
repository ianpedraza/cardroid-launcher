/*
 * IconsSizeAdapter.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 4/10/23 10:57
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.domain.model.settings.appearance.iconssize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class IconsSizeAdapter : JsonSerializer<IconsSize>, JsonDeserializer<IconsSize> {
    override fun serialize(
        iconSize: IconsSize?,
        type: Type?,
        context: JsonSerializationContext?
    ): JsonElement = JsonPrimitive(iconSize?.value ?: IconsSize.default.value)

    override fun deserialize(
        json: JsonElement?,
        type: Type?,
        context: JsonDeserializationContext?
    ): IconsSize {
        val value = json?.toString()?.toInt() ?: IconsSize.default.value
        return IconsSize.fromValue(value)
    }
}
