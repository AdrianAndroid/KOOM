package kshark

/**
 * A GcRoot as identified by [HprofRecord.HeapDumpRecord.GcRootRecord] in the heap dump.
 */
sealed class GcRoot {

    companion object {
        fun log(msg: String) {
            //HprofHeapGraph.log("log GCRoot $msg")
        }
    }

    /**
     * The object id of the object that this gc root references.
     */
    abstract val id: Long

    /**
     * An unknown gc root.
     */
    class Unknown(override val id: Long) : GcRoot()

    /**
     * A global variable in native code.
     */
    class JniGlobal(
        override val id: Long,
        val jniGlobalRefId: Long
    ) : GcRoot() {
        override fun toString(): String {
            log("JniGlobal(id=$id, jniGlobalRefId=$jniGlobalRefId)")
            return "JniGlobal"
        }
    }

    /**
     * A local variable in native code.
     */
    class JniLocal(
        override val id: Long,
        /** Corresponds to [ThreadObject.threadSerialNumber] */
        val threadSerialNumber: Int,
        /**
         * frame number in stack trace (-1 for empty)
         */
        val frameNumber: Int
    ) : GcRoot() {
        override fun toString(): String {
            log("JniLocal(id=$id, threadSerialNumber=$threadSerialNumber, frameNumber=$frameNumber)")
            return "JniLocal"
        }
    }

    /**
     * A java local variable
     */
    class JavaFrame(
        override val id: Long,
        /** Corresponds to [ThreadObject.threadSerialNumber] */
        val threadSerialNumber: Int,
        /**
         * frame number in stack trace (-1 for empty)
         */
        val frameNumber: Int
    ) : GcRoot() {
        override fun toString(): String {
            log("JavaFrame(id=$id, threadSerialNumber=$threadSerialNumber, frameNumber=$frameNumber)")
            return "JavaFrame"
        }
    }

    /**
     * Input or output parameters in native code
     */
    class NativeStack(
        override val id: Long,
        /**
         * Corresponds to [ThreadObject.threadSerialNumber]
         * Note: the corresponding thread is sometimes not found, see:
         * https://issuetracker.google.com/issues/122713143
         */
        val threadSerialNumber: Int
    ) : GcRoot() {
        override fun toString(): String {
            log("NativeStack(id=$id, threadSerialNumber=$threadSerialNumber)")
            return "NativeStack"
        }
    }

    /**
     * A system class
     */
    class StickyClass(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("StickyClass(id=$id)")
            return "StickyClass"
        }
    }

    class ThreadBlock(
        override val id: Long,
        /** Corresponds to [ThreadObject.threadSerialNumber] */
        val threadSerialNumber: Int
    ) : GcRoot() {
        override fun toString(): String {
            log("ThreadBlock(id=$id, threadSerialNumber=$threadSerialNumber)")
            return "ThreadBlock"
        }
    }

    /**
     * Everything that called the wait() or notify() methods, or
     * that is synchronized.
     */
    class MonitorUsed(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("MonitorUsed(id=$id)")
            return "MonitorUsed"
        }
    }

    /**
     * A thread.
     *
     * Added at https://android.googlesource.com/platform/tools/base/+/c0f0d528c155cab32e372dac77370569a386245c
     */
    class ThreadObject(
        override val id: Long,
        val threadSerialNumber: Int,
        val stackTraceSerialNumber: Int
    ) : GcRoot() {
        override fun toString(): String {
            log(
                "ThreadObject(id=$id, threadSerialNumber=$threadSerialNumber, " +
                    "stackTraceSerialNumber=$stackTraceSerialNumber)"
            )
            return "ThreadObject"
        }
    }

    /**
     * It's unclear what this is, documentation welcome.
     */
    class ReferenceCleanup(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("ReferenceCleanup(id=$id)")
            return "ReferenceCleanup"
        }
    }

    /**
     * It's unclear what this is, documentation welcome.
     */
    class VmInternal(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("VmInternal(id=$id)")
            return "VmInternal"
        }
    }

    /**
     * It's unclear what this is, documentation welcome.
     */
    class JniMonitor(
        override val id: Long,
        val stackTraceSerialNumber: Int,
        val stackDepth: Int
    ) : GcRoot() {
        override fun toString(): String {
            log("JniMonitor(id=$id, stackTraceSerialNumber=$stackTraceSerialNumber, stackDepth=$stackDepth)")
            return "JniMonitor"
        }
    }

    /**
     * An interned string, see [java.lang.String.intern].
     */
    class InternedString(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("InternedString(id=$id)")
            return "InternedString"
        }
    }

    /**
     * An object that is in a queue, waiting for a finalizer to run.
     */
    class Finalizing(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("Finalizing(id=$id)")
            return "Finalizing"
        }
    }

    /**
     * An object held by a connected debugger
     */
    class Debugger(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("Debugger(id=$id)")
            return "Debugger"
        }
    }

    /**
     * An object that is unreachable from any other root, but not a root itself.
     */
    class Unreachable(override val id: Long) : GcRoot() {
        override fun toString(): String {
            log("Unreachable(id=$id)")
            return "Unreachable"
        }
    }
}