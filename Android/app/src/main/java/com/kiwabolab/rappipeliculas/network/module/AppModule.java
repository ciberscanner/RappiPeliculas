package com.kiwabolab.rappipeliculas.network.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    //----------------------------------------------------------------------------------------------
    //Variables
    Application mApplication;

    //----------------------------------------------------------------------------------------------
    //Constructor
    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    //----------------------------------------------------------------------------------------------
    //
    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}