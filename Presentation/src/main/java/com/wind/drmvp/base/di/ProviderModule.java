package com.wind.drmvp.base.di;


import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.wind.data.DbOpenHelper;
import com.wind.data.login.datastore.LoginUserDbDataStore;
import com.wind.domain.user.interactor.UserUsecase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
@Module
public class ProviderModule {

    /*@Singleton
    @Provides
    EventBus provideEventBus(final EventBusConfig config) {
        return EventBus.builder()
                .logNoSubscriberMessages(config.logNoSubscriberMessages())
                .sendNoSubscriberEvent(config.sendNoSubscriberEvent())
                .eventInheritance(config.eventInheritance())
                .throwSubscriberException(config.throwSubscriberException())
                .build();
    }*/

   /* @Singleton
    @Provides
    Gson provideGson(final GsonConfig config) {
        return new GsonBuilder().registerTypeAdapterFactory(new AutoTypeAdapterFactory())
                .registerTypeAdapter(ZonedDateTime.class,
                        new ZonedDateTimeJsonConverter(config.dateTimeFormatter()))
                .setDateFormat(config.dateFormatString())
                .setPrettyPrinting()
                .create();
    }*/

    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder().baseUrl("http://marryu.miaotu.net/app/")

                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                        RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

   /* @Singleton
    @Provides
    OkHttpClient provideHttpClient(final HttpClientConfig config) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (config.enableLog()) {
            builder.addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(final String message) {
                            Timber.tag("OkHttp").d(message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(new CurlInterceptor(new Loggable() {
                        @Override
                        public void log(final String message) {
                            Timber.tag("Ok2Curl").d(message);
                        }
                    }));
        }
        return builder.build();
    }*/

    /*  @Singleton
      @Provides
      BriteDatabase provideBriteDb(final BriteDbConfig config) {
          final BriteDatabase briteDb =
                  SqlBrite.create().wrapDatabaseHelper(config.sqliteOpenHelper(), Schedulers.io());
          briteDb.setLoggingEnabled(config.enableLogging());
          return briteDb;
      }*/
    @Singleton
    @Provides
    BriteDatabase provideBriteDb(@NonNull final DbOpenHelper dbOpenHelper) {
        final BriteDatabase briteDb =
                SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());

        return briteDb;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public LoginUserDbDataStore provideLoginUserDb(BriteDatabase db,Gson gson){
        return new LoginUserDbDataStore(db,gson);
    }

    @Provides
    @Singleton
    public UserUsecase provideUsecase(LoginUserDbDataStore dataStore){
        return new UserUsecase(dataStore);
    }

}