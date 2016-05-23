package com.wind.drmvp.base.di;

import android.content.Context;

import com.wind.drmvp.base.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wind on 16/5/18.
 */
@Module
public class AppModule {
    private App app;
    public AppModule(App app){
      this.app=app;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.app;
    }


}
