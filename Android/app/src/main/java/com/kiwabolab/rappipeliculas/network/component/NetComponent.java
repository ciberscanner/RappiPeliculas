package com.kiwabolab.rappipeliculas.network.component;

import com.kiwabolab.rappipeliculas.network.module.AppModule;
import com.kiwabolab.rappipeliculas.network.module.NetModule;
import com.kiwabolab.rappipeliculas.vista.activity.Detalle;
import com.kiwabolab.rappipeliculas.vista.activity.Home;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    //----------------------------------------------------------------------------------------------
    //
    void inject(Detalle activity);
    void inject(Home activity);

}