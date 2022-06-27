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
 *
 * @author Rui Li <lirui05@kuaishou.com>
 */

package com.kwai.koom.demo;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kwai.koom.demo.javaleak.JavaLeakTestActivity;
import com.kwai.koom.demo.nativeleak.NativeLeakTestActivity;
import com.kwai.koom.demo.threadleak.ThreadLeakTestActivity;

public class MainActivity extends AppCompatActivity implements ComponentCallbacks2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_java_leak).setOnClickListener(v -> {
            JavaLeakTestActivity.start(MainActivity.this);
        });

        findViewById(R.id.btn_test_native_leak).setOnClickListener(v -> {
            NativeLeakTestActivity.start(MainActivity.this);
        });

        findViewById(R.id.btn_test_thread_leak).setOnClickListener(v -> {
            ThreadLeakTestActivity.Companion.start(MainActivity.this);
        });

        findViewById(R.id.btn_getavailablememoery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "" + getAvailableMemory(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //     public static class MemoryInfo implements Parcelable {
//        public long availMem;
//        public long totalMem;
//        public long threshold;
//        public boolean lowMemory;
//        /** @hide */
//        @UnsupportedAppUsage
//        public long hiddenAppThreshold;
//        /** @hide */
//        @UnsupportedAppUsage
//        public long secondaryServerThreshold;
//        /** @hide */
//        @UnsupportedAppUsage
//        public long visibleAppThreshold;
//        /** @hide */
//        @UnsupportedAppUsage
//        public long foregroundAppThreshold;
//    }
    private String getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        StringBuilder sb = new StringBuilder();
        sb.append("availMem = ").append(memoryInfo.availMem).append("\n")
            .append("totalMem = ").append(memoryInfo.totalMem).append("\n")
            .append("threshold = ").append(memoryInfo.threshold).append("\n")
            .append("lowMemory = ").append(memoryInfo.lowMemory).append("\n");
        return sb.toString();
    }

    /**
     * Release memory when the UI becomes hidden or when system resources become low.
     *
     * @param level the memory-related event that was raised.
     */
    public void onTrimMemory(int level) {

        // Determine which lifecycle or system event was raised.
        switch (level) {

            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:
                    /*
                       Release any UI objects that currently hold memory.
                       The user interface has moved to the background.
                    */
                break;

            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:
                    /*
                       Release any memory that your app doesn't need to run.

                       The device is running low on memory while the app is running.
                       The event raised indicates the severity of the memory-related event.
                       If the event is TRIM_MEMORY_RUNNING_CRITICAL, then the system will
                       begin killing background processes.
                    */

                break;

            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:
                    /*
                       Release as much memory as the process can.

                       The app is on the LRU list and the system is running low on memory.
                       The event raised indicates where the app sits within the LRU list.
                       If the event is TRIM_MEMORY_COMPLETE, the process will be one of
                       the first to be terminated.
                    */
                break;

            default:
                    /*
                      Release any non-critical data structures.

                      The app received an unrecognized memory level value
                      from the system. Treat this as a generic low-memory message.
                    */
                break;
        }
    }
}