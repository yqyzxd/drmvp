package com.wind.drmvp.base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.wind.drmvp.base.di.AppComponent;
import com.wind.drmvp.base.di.AppModule;
import com.wind.drmvp.base.di.DaggerAppComponent;

/**
 * Created by wind on 16/5/18.
 */
public class App extends Application {
    private static App sInstance;
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        debug();


        mAppComponent = createComponent();
    }

    private void debug() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        AndroidDevMetrics.initWith(this);
    }

    private AppComponent createComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static App get() {
        return sInstance;
    }

    private static void setInstance(final App instance) {
        sInstance = instance;
    }


    public AppComponent appComponent(){
        return mAppComponent;
    }
}
