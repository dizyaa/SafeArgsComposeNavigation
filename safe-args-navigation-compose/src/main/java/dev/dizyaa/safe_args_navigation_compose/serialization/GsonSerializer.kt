package dev.dizyaa.safe_args_navigation_compose.serialization

import com.google.gson.Gson
import com.google.gson.GsonBuilder

public class GsonSerializer(
    private val gson: Gson,
): Serializer {
    constructor(gsonBuilder: GsonBuilder): this(gsonBuilder.create())

    override fun toJson(src: Any?): String {
        return gson.toJson(src)
    }

    override fun <T> fromJson(json: String, type: Class<T>): T {
        return gson.fromJson(json, type)
    }
}