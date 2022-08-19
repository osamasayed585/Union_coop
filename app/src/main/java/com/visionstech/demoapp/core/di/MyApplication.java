package com.visionstech.demoapp.core.di;

import android.app.Application;

import com.visionstech.demoapp.BuildConfig;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;


@HiltAndroidApp
public class MyApplication extends Application {


    @Inject
    Timber.DebugTree mDebugTree;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG)
            Timber.plant(mDebugTree);
    }
}
