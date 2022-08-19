package com.visionstech.demoapp.core.di;

import android.app.AlertDialog;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {

    // if I want to inject anything for activity or fragment

    @Provides
    public AlertDialog ProvidesAlertDialog(@ActivityContext Context context) {
        return new AlertDialog.Builder(context).create();
    }
}
