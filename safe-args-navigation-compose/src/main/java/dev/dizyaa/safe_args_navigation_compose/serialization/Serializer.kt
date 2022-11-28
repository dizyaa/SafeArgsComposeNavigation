package dev.dizyaa.safe_args_navigation_compose.serialization

public interface Serializer {
    fun toJson(src: Any?): String
    fun <T> fromJson(json: String, type: Class<T>): T
}

