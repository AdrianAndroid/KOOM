/*
 * Copyright 2020 Kwai, Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * A jvm hprof dumper which use fork and don't block main process.
 *
 * @author Rui Li <lirui05@kuaishou.com>
 */

package com.kwai.koom.javaoom.hprof;

import java.io.IOException;

import android.os.Build;
import android.os.Debug;

import com.kwai.koom.base.MonitorLog;

public class ForkJvmHeapDumper extends HeapDumper {

  private static final String TAG = "OOMMonitor_ForkJvmHeapDumper";

  public ForkJvmHeapDumper() {
    super();
    if (soLoaded) {
      init(); // 打开系统的库函数
    }
  }

  @Override
  public boolean dump(String path) {
    MonitorLog.i(TAG, "dump " + path);
    if (!soLoaded) {
      MonitorLog.e(TAG, "dump failed caused by so not loaded!");
      return false;
    }

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
        || Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
      MonitorLog.e(TAG, "dump failed caused by version not supported!");
      return false;
    }

    boolean dumpRes = false;
    try {
      MonitorLog.i(TAG, "before suspend and fork.");
      // 创建子进程
      // pid返回值
      //  1。 返回子进程id给父进程：
      //         因为一个进程的子进程可能有多个，并且没有一个函数可以获得一个进程的所有子进程
      //  2。 返回给子进程值为0：
      //         一个进程只会有一个，所以子进程松狮可以调用getid以获得当前进程id以调用getid获得父进程id
      //  3。 出现错误，返回负值：
      //         当前进程数已经达到系统规定的上限，这是errno的值被设置为EAGAIN
      //         系统内存不足，这时errno的值被设置为ENOMEM
      int pid = suspendAndFork();
      if (pid == 0) {
        // Child process
        Debug.dumpHprofData(path);
        exitProcess();
      } else if (pid > 0) {
        // Parent process
        dumpRes = resumeAndWait(pid);
        MonitorLog.i(TAG, "notify from pid " + pid);
      }
    } catch (IOException e) {
      MonitorLog.e(TAG, "dump failed caused by " + e.toString());
      e.printStackTrace();
    }
    return dumpRes;
  }

  // ---->https://www.jianshu.com/p/586300fdb1ce<----
  // int main(int argc, char *argv[]) {
  //   pid_t pid;
  //   int count = 0;
  //   //获得当前进程ID
  //   printf("Current Process Id = %d\n", getpid());
  //   if ((pid == fork()) < 0) {
  //     print("异常退出")；
  //   } else if(pid == 0) {
  //     count++;
  //     printf("进程子进程，当前进程curretPid=%d, 父进程parentPid=%d\n", getpid(), getppid())
  //   } else {
  //     count++;
  //     printf("当前进程 currentPid = %d, Count = %d\n", getpid(), count);
  //   }
  //   printf("当前进程 currentPid = %d, Count = %d\n", getpid(), count);
  //   return 0
  // }

  /**
   * Init before do dump.
   * // 打开系统的库函数
   */
  private native void init();

  /**
   * Suspend the whole ART, and then fork a process for dumping hprof.
   *
   * @return return value of fork
   */
  private native int suspendAndFork();

  /**
   * Resume the whole ART, and then wait child process to notify.
   *
   * @param pid pid of child process.
   */
  private native boolean resumeAndWait(int pid);

  /**
   * Exit current process.
   */
  private native void exitProcess();
}
