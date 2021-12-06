/*
 * Copyright (c) 2021. Kwai, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author KOOM Team
 *
 */
package com.kwai.koom.base

object MonitorLog {
    @JvmStatic
    fun v(tag: String, msg: String): Int {
        return MonitorManager.commonConfig.log.v(tag, msg)
    }

    @JvmStatic
    fun i(tag: String, msg: String): Int {
        return MonitorManager.commonConfig.log.i(tag, msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String): Int {
        return MonitorManager.commonConfig.log.d(tag, msg)
    }

    @JvmStatic
    fun w(tag: String, msg: String): Int {
        return MonitorManager.commonConfig.log.w(tag, msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String): Int {
        return MonitorManager.commonConfig.log.e(tag, msg)
    }

    @JvmStatic
    fun v(tag: String, msg: String, syncToLogger: Boolean): Int {
        if (syncToLogger) MonitorLogger.addCustomStatEvent(tag, msg)

        return this.v(tag, msg)
    }

    @JvmStatic
    fun i(tag: String, msg: String, syncToLogger: Boolean): Int {
        if (syncToLogger) MonitorLogger.addCustomStatEvent(tag, msg)

        return this.i(tag, msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String, syncToLogger: Boolean): Int {
        if (syncToLogger) MonitorLogger.addCustomStatEvent(tag, msg)

        return this.d(tag, msg)
    }

    @JvmStatic
    fun w(tag: String, msg: String, syncToLogger: Boolean): Int {
        if (syncToLogger) MonitorLogger.addCustomStatEvent(tag, msg)

        return this.w(tag, msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String, syncToLogger: Boolean): Int {
        if (syncToLogger) MonitorLogger.addCustomStatEvent(tag, msg)

        return this.e(tag, msg)
    }
}

interface Log {
    fun v(tag: String, msg: String) = runIfDebug { showLog(tag, msg) { t, m -> android.util.Log.v(t, m) } }

    fun i(tag: String, msg: String) = runIfDebug { showLog(tag, msg) { t, m -> android.util.Log.i(t, m) } }

    fun d(tag: String, msg: String) = runIfDebug { showLog(tag, msg) { t, m -> android.util.Log.d(t, m) } }

    fun w(tag: String, msg: String) = runIfDebug { showLog(tag, msg) { t, m -> android.util.Log.w(t, m) } }

    fun e(tag: String, msg: String) = runIfDebug { showLog(tag, msg) { t, m -> android.util.Log.e(t, m) } }
}

internal inline fun runIfDebug(block: () -> Int): Int {
    if (MonitorBuildConfig.DEBUG) {
        return block()
    }

    return -1
}

internal inline fun showLog(tag: String, msg: String, f: (tag: String?, msg: String) -> Int): Int {
    if (msg.length < 4000) {
        f(tag, msg)
    } else {
        var index = 0
        while (index < msg.length) {
            f(tag, if (msg.length > index + 4000) msg.substring(index, 4000) else msg.substring(index))
            index += 4000
        }
    }
    return -1
}
