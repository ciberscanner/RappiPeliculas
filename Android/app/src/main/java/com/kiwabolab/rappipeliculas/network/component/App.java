package com.kiwabolab.rappipeliculas.network.component;

import android.app.Application;

import com.kiwabolab.rappipeliculas.network.module.AppModule;
import com.kiwabolab.rappipeliculas.network.module.NetModule;


public class App extends Application {
    //----------------------------------------------------------------------------------------------
    //Variables
    private NetComponent mNetComponent;
    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.themoviedb.org/3/"))
                .build();
    }
    //----------------------------------------------------------------------------------------------
    //
    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}