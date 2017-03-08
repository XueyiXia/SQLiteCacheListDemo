package com.sqlite;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * @author: xiaxueyi
 * @date: 2017-03-02
 * @time: 14:39
 * @说明:
 */
public class AppLoader extends Application {

    private static  AppLoader mInstance;

    public static AppLoader getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {

        mInstance=this;

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        super.onCreate();
    }
}

