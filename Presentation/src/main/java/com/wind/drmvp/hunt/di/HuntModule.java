package com.wind.drmvp.hunt.di;

import android.content.Context;

import com.wind.base.di.annotation.ActivityScope;
import com.wind.data.Repository;
import com.wind.data.base.cache.ICache;
import com.wind.data.hunt.cache.HuntCache;
import com.wind.data.hunt.datastore.HuntDataStoreFactory;
import com.wind.data.hunt.repository.HuntRepository;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.domain.Usecase;
import com.wind.domain.hunt.interactor.HuntUsecase;
import com.wind.drmvp.hunt.activity.HuntActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by wind on 16/5/20.
 */
@Module
public class HuntModule {
    private HuntActivity activity;
    public HuntModule(HuntActivity activity){
        this.activity=activity;
    }


    @Provides
    public Context activity(){
      return activity;
    }


    @ActivityScope
    @Provides
    public ICache provideHuntCache(){
        return new HuntCache();
    }
    @ActivityScope
    @Provides
    public HuntDataStoreFactory provideDataStoreFactory(Retrofit retrofit,ICache cache){
        return new HuntDataStoreFactory(retrofit,cache);
    }
  /*  @ActivityScope
    @Provides
    public DataStore<HuntRequest,HuntResponse> provideDataStore(HuntDataStore dataStore){
        return dataStore;
    }*/
    @ActivityScope
    @Provides
    public Repository<HuntRequest,HuntResponse> provideRepository(HuntDataStoreFactory factory){
        return new HuntRepository(factory);
    }

    @ActivityScope
    @Provides
    public Usecase<HuntRequest,HuntResponse> provideUsecase(HuntUsecase usecase){
        return usecase;
    }



}
