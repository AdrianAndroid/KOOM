package kshark

import kshark.HeapObject.HeapClass
import kshark.HeapObject.HeapInstance
import kshark.HeapObject.HeapObjectArray
import kshark.HeapObject.HeapPrimitiveArray

/**
 * Enables navigation through the heap graph of objects.
 */
interface HeapGraph {
    val identifierByteSize: Int

    /**
     * In memory store that can be used to store objects this [HeapGraph] instance.
     */
    val context: GraphContext

    val objectCount: Int

    val classCount: Int

    val instanceCount: Int

    val objectArrayCount: Int

    val primitiveArrayCount: Int

    /**
     * All GC roots which type matches types known to this heap graph and which point to non null
     * references. You can retrieve the object that a GC Root points to by calling [findObjectById]
     * with [GcRoot.id], however you need to first check that [objectExists] returns true because
     * GC roots can point to objects that don't exist in the heap dump.
     * 所有类型匹配此堆图已知类型并指向非空引用的 GC 根。 你可以 通过使用 [GcRoot.id] 调用 [findObjectById]
     * 来检索 GC Root 指向的对象，但是您需要 首先检查
     * [objectExists] 是否返回 true，因为 GC 根可以指向堆中不存在的对象倾倒。
     */
    val gcRoots: List<GcRoot>

    /**
     * Sequence of all objects in the heap dump.
     * This sequence does not trigger any IO reads.
     * 堆转储中所有对象的顺序。
     * 此序列不会触发任何 IO 读取。
     */
    val objects: Sequence<HeapObject>

    /**
     * Sequence of all classes in the heap dump.
     * This sequence does not trigger any IO reads.
     * 堆转储中所有类的顺序。
     * 此序列不会触发任何 IO 读取。
     */
    val classes: Sequence<HeapClass>

    /**
     * Sequence of all instances in the heap dump.
     * This sequence does not trigger any IO reads.
     * 堆转储中所有实例的顺序。
     * 此序列不会触发任何 IO 读取。
     */
    val instances: Sequence<HeapInstance>

    /**
     * Sequence of all object arrays in the heap dump.
     * This sequence does not trigger any IO reads.
     * 堆转储中所有对象数组的顺序。
     * 此序列不会触发任何 IO 读取。
     */
    val objectArrays: Sequence<HeapObjectArray>

    /**
     * Sequence of all primitive arrays in the heap dump.
     * This sequence does not trigger any IO reads.
     * 堆转储中所有原始数组的序列。
     * 此序列不会触发任何 IO 读取。
     */
    val primitiveArrays: Sequence<HeapPrimitiveArray>

    /**
     * Returns the [HeapObject] corresponding to the provided [objectId], and throws
     * [IllegalArgumentException] otherwise.
     */
    @Throws(IllegalArgumentException::class)
    fun findObjectById(objectId: Long): HeapObject

    /**
     * Returns the [HeapObject] corresponding to the provided [objectIndex], and throws
     * [IllegalArgumentException] if [objectIndex] is less than 0 or more than [objectCount] - 1.
     */
    @Throws(IllegalArgumentException::class)
    fun findObjectByIndex(objectIndex: Int): HeapObject

    /**
     * Returns the [HeapObject] corresponding to the provided [objectId] or null if it cannot be
     * found.
     */
    fun findObjectByIdOrNull(objectId: Long): HeapObject?

    /**
     * Returns the [HeapClass] corresponding to the provided [className], or null if the
     * class cannot be found.
     */
    fun findClassByName(className: String): HeapClass?

    /**
     * Returns true if the provided [objectId] exists in the heap dump.
     */
    fun objectExists(objectId: Long): Boolean
}