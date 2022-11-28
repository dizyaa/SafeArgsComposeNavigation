package dev.dizyaa.safe_args_navigation_compose

import dev.dizyaa.safe_args_navigation_compose.serialization.Serializer

public object SafeArgsNavigation {

    public var serializer: Serializer = StubSerializer()
        private set

    private fun init(
        serializer: Serializer,
    ) {
        this.serializer = serializer
    }
}

public class StubSerializer: Serializer {
    override fun toJson(src: Any?) =
        throw NullPointerException("serializer is null")

    override fun <T> fromJson(json: String, type: Class<T>) =
        throw NullPointerException("serializer is null")
}
