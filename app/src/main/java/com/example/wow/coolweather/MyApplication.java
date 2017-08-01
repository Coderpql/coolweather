package com.example.wow.coolweather;

import android.app.Application;
import android.content.Context;

/**
 * Created by wow on 2017/8/1.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    public static Context getInstance(){
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}
