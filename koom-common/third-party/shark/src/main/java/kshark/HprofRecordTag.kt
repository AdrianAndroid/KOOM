package kshark

import java.util.EnumSet

// https://juejin.cn/post/6854573208520097799
enum class HprofRecordTag(val tag: Int) {
    STRING_IN_UTF8(0x01), // HPROF_TAG_STRING 字符串
    LOAD_CLASS(0x02),     // HPROF_TAG_LOAD_CLASS 类

    // Currently ignored
    UNLOAD_CLASS(0x03),   // HPROF_TAG_UNLOAD_CLASS
    STACK_FRAME(0x04),    // HPROF_TAG_STACK_FRAME 栈帧
    STACK_TRACE(0x05),    // HPROF_TAG_STACK_TRACE 堆栈

    // Currently ignored
    ALLOC_SITES(0x06),    // HPROF_TAG_ALLOC_SITES

    // Currently ignored
    HEAP_SUMMARY(0x07),   // HPROF_TAG_SUMMARY

    // Currently ignored
    START_THREAD(0x0a),   // HPROF_TAG_START_THREAD

    // Currently ignored
    END_THREAD(0x0b),     // HPROF_TAG_THREAD

    // Currently not reported
    HEAP_DUMP(0x0c),      // HPROF_TAG_DUMP 堆

    // Currently not reported
    HEAP_DUMP_SEGMENT(0x1c), // HPROF_TAG_SEGMENT
    HEAP_DUMP_END(0x2c),  // HPROF_TAG_DUMP_SEGMENT

    // Currently ignored
    CPU_SAMPLES(0x0d),    // HPROF_TAG_HEAP_SAMPLES

    // Currently ignored
    CONTROL_SETTINGS(0x0e), // HPROF_TAG_CONTROL_SETTINGS

    // 如果是堆信息，即TAG为HEAP_DUMP或HEAP_DUMP_SEGMENT时，那么其BODY由一系列子recor组成
    ROOT_UNKNOWN(0xff),      // HPROF_ROOT_UNKNOW
    ROOT_JNI_GLOBAL(0x01),   // HPROF_ROOT_JNI_GLOBAL native变量
    ROOT_JNI_LOCAL(0x02),    // HPROF_ROOT_JNI_LOCAL
    ROOT_JAVA_FRAME(0x03),   // HPROF_ROOT_JAVA_FRAME
    ROOT_NATIVE_STACK(0x04), // HPROF_ROOT_NATIVE_STACK
    ROOT_STICKY_CLASS(0x05), // HPROF_ROOT_STICKY_CLASS

    // An object that was referenced from an active thread block.
    ROOT_THREAD_BLOCK(0x06), // HPROF_ROOT_THREAD_BLOCK
    ROOT_MONITOR_USED(0x07), // HPROF_ROOT_MONITOR_USED
    ROOT_THREAD_OBJECT(0x08),// HPROF_ROOT_THREAD_OBJECT

    /**
     * Android format addition
     *
     * Specifies information about which heap certain objects came from. When a sub-tag of this type
     * appears in a HPROF_HEAP_DUMP or HPROF_HEAP_DUMP_SEGMENT record, entries that follow it will
     * be associated with the specified heap.  The HEAP_DUMP_INFO data is reset at the end of the
     * HEAP_DUMP[_SEGMENT].  Multiple HEAP_DUMP_INFO entries may appear in a single
     * HEAP_DUMP[_SEGMENT].
     *
     * Format: u1: Tag value (0xFE) u4: heap ID ID: heap name string ID
     */
    HEAP_DUMP_INFO(0xfe),        // HPROF_HEAP_DUMP_INFO
    ROOT_INTERNED_STRING(0x89),  // HPROF_ROOT_INTERNED_STRING
    ROOT_FINALIZING(0x8a),       // HPROF_ROOT_FINALIZING Obsolete
    ROOT_DEBUGGER(0x8b),         // HPROF_ROOT_DEBUGGER
    ROOT_REFERENCE_CLEANUP(0x8c),// HPROF_ROOT_REFERENCE_CLEANUP
    ROOT_VM_INTERNAL(0x8d),      // HPROF_ROOT_VM_INTERNAL
    ROOT_JNI_MONITOR(0x8e),      // HPROF_ROOT_JNI_MONITOR
    ROOT_UNREACHABLE(0x90),      // HPROF_ROOT_UNREACHABLE

    // Not supported.
    PRIMITIVE_ARRAY_NODATA(0xc3),
    CLASS_DUMP(0x20),     //  HPROF_CLASS_DUMP 类
    INSTANCE_DUMP(0x21),  // HPROF_INSTANCE_DUMP 实例对象
    OBJECT_ARRAY_DUMP(0x22), // HPROF_OBJECT_ARRAY_DUMP 对象数组
    PRIMITIVE_ARRAY_DUMP(0x23), // HPROF_PRIMITIVE_ARRAY_DUMP 基础类型数组
    ;

    companion object {
        val rootTags: EnumSet<HprofRecordTag> = EnumSet.of(
            ROOT_UNKNOWN,
            ROOT_JNI_GLOBAL,
            ROOT_JNI_LOCAL,
            ROOT_JAVA_FRAME,
            ROOT_NATIVE_STACK,
            ROOT_STICKY_CLASS,
            ROOT_THREAD_BLOCK,
            ROOT_MONITOR_USED,
            ROOT_THREAD_OBJECT,
            ROOT_INTERNED_STRING,
            ROOT_FINALIZING,
            ROOT_DEBUGGER,
            ROOT_REFERENCE_CLEANUP,
            ROOT_VM_INTERNAL,
            ROOT_JNI_MONITOR,
            ROOT_UNREACHABLE
        )
    }
}