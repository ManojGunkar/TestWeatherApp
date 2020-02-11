package com.manoj.weatherapp;

import android.app.Application;

import io.reactivex.plugins.RxJavaPlugins;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {});
    }

}
