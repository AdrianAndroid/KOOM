package com.kwai.koom.demo.javaleak.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Time:2021/12/7 17:06
 * Author: flannery
 * Description: 单例引起的内存泄漏
 */
public class ActivitySingletonLeakMaker extends LeakMaker<Activity> {

    private static ActivitySingletonLeakMaker singleton;
    private Context mContext;
    public void sayHello() {
        Log.i("TAG", "Hello");
    }

    public static ActivitySingletonLeakMaker getInstance(Context context) {
        if (singleton == null) {
            synchronized (ActivitySingletonLeakMaker.class) {
                if (singleton == null) {
                    singleton = new ActivitySingletonLeakMaker();
                    singleton.mContext = context;
                }
            }
        }
        return singleton;
    }

    @Override
    void startLeak(Context context) {
        Intent intent = new Intent(context, SingletonActivity.class);
        context.startActivity(intent);
    }

    public static class SingletonActivity extends AppCompatActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ActivitySingletonLeakMaker.getInstance(this).sayHello();
            this.finish();
        }
    }
}
