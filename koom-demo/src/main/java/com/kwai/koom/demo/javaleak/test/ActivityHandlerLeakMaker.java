package com.kwai.koom.demo.javaleak.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Time:2021/12/7 17:25
 * Author: flannery
 * Description:
 */
public class ActivityHandlerLeakMaker extends LeakMaker<Activity> {
    @Override
    void startLeak(Context context) {
        Intent intent = new Intent(context, HandlerActivity.class);
        context.startActivity(intent);
    }

    public static class HandlerActivity extends AppCompatActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ActivitySingletonLeakMaker.getInstance(this).sayHello();

            new Handler(getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 2 * 60 * 60 * 1000);
            findViewById(android.R.id.content).post(this::finish);
        }
    }
}
