package kshark

/**
 * In memory store that can be used to store objects in a given [HeapGraph] instance.
 * This is a simple [MutableMap] of [String] to [Any], but with unsafe generics access.
 * 在内存存储中，可用于在给定的 [HeapGraph] 实例中存储对象。
 * 这是一个简单的 [String] 到 [Any] 的 [MutableMap]，但具有不安全的泛型访问。
 */
class GraphContext {
    private val store: MutableMap<String, Any?> = mutableMapOf<String, Any?>()
    operator fun <T> get(key: String): T? {
        @Suppress("UNCHECKED_CAST")
        return store[key] as T?
    }

    /**
     * @see MutableMap.getOrPut
     */
    fun <T> getOrPut(
        key: String,
        defaultValue: () -> T
    ): T {
        @Suppress("UNCHECKED_CAST")
        return store.getOrPut(key, {
            defaultValue()
        }) as T
    }

    /**
     * @see MutableMap.set
     */
    operator fun <T> set(
        key: String,
        value: T
    ) {
        store[key] = (value as Any?)
    }

    /**
     * @see MutableMap.containsKey
     */
    operator fun contains(key: String): Boolean {
        return key in store
    }

    /**
     * @see MutableMap.remove
     */
    operator fun minusAssign(key: String) {
        @Suppress("UNCHECKED_CAST")
        store -= key
    }
}