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
    fun v(tag: String, msg: String) = runIfDebug {
        print(tag, msg) { t, m ->
            checkIsNull(t, m)
            android.util.Log.v(t, m)
        }
    }

    fun i(tag: String, msg: String) = runIfDebug {
        print(tag, msg) { t, m ->
            checkIsNull(t, m)
            android.util.Log.i(t, m)
        }
    }

    fun d(tag: String, msg: String) = runIfDebug {
        print(tag, msg) { t, m ->
            checkIsNull(t, m)
            android.util.Log.d(t, m)
        }
    }

    fun w(tag: String, msg: String) = runIfDebug {
        print(tag, msg) { t, m ->
            checkIsNull(t, m)
            android.util.Log.w(t, m)
        }
    }

    fun e(tag: String, msg: String) = runIfDebug {
        print(tag, msg) { t, m ->
            checkIsNull(t, m)
            android.util.Log.e(t, m)
        }
    }
}

internal inline fun checkIsNull(vararg strs: String?) {
    strs.forEach {
        if (it.isNullOrBlank()) throw IllegalStateException("打印的log不能为空!!")
    }
}

internal inline fun runIfDebug(block: () -> Int): Int {
    if (MonitorBuildConfig.DEBUG) {
        return block()
    }

    return -1
}

//internal inline fun showLog(tag: String, msg: String, f: (tag: String?, msg: String) -> Int): Int {
//    if (msg.length < 4000) {
//        f(tag, msg)
//    } else {
//        var index = 0
//        while (index < msg.length) {
//            f(tag, if (msg.length > index + 4000) msg.substring(index, 4000) else msg.substring(index))
//            index += 4000
//        }
//    }
//    return -1
//}

internal inline fun print(tag: String, msg: String, f: (tag: String?, msg: String) -> Int): Int {
    // 1. 测试控制台最多打印4062个字节，不同情况稍有出入（注意：这里是字节，不是字符！！）
    // 2. 字符串默认字符集编码是utf-8，它是变长编码一个字符用1~4个字节表示
    // 3. 这里字符长度小于1000，即字节长度小于4000，则直接打印，避免执行后续流程，提高性能哈
    if (msg.length < 1000) {
        f(tag, msg)
        return 0
    }
    // 一次打印的最大字节数
    val maxByteNum = 4000
    // 字符串转字节数组
    var bytes: ByteArray = msg.toByteArray()
    // 超出范围直接打印
    if (maxByteNum >= bytes.size) {
        f(tag, msg)
        return 0
    }

    // 分段打印记数
    var count = 1
    // 在数组范围内，则循环分段
    while (maxByteNum < bytes.size) {
        // 按字节长度截取字符串
        val subStr = cutStr(bytes, maxByteNum)
        // 打印日志
        val desc = String.format("分段打印(%s):%s", count++, subStr)
        f(tag, desc)

        // 截取出尚未打印的字节数组
        bytes = bytes.copyOfRange(subStr.toByteArray().size, bytes.size)
    }
    return 0
}

internal inline fun cutStr(bytes: ByteArray, subLen: Int): String {
    // 边界判断
    if (subLen < 1) {
        return ""
    }
    // 超出范围直接返回
    if (subLen >= bytes.size) {
        return String(bytes)
    }

    // 复制出定长字节数组， 转为字符串
    val subStr: String = String(bytes.copyOf(subLen))
    // 避免末尾字符串是被拆分的，这里减1使字符串保持完整
    return subStr.substring(0, subStr.length - 1)
}