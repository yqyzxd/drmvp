package com.wind.drmvp.base.di;

import com.wind.drmvp.hunt.di.HuntComponent;
import com.wind.drmvp.hunt.di.HuntModule;
import com.wind.drmvp.login.di.LoginComponent;
import com.wind.drmvp.login.di.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wind on 16/5/18.
 */
@Singleton
@Component(modules ={AppModule.class,ProviderModule.class})
public interface AppComponent {
    LoginComponent plus(LoginModule loginModule);
    HuntComponent plus(HuntModule huntModule);
}
