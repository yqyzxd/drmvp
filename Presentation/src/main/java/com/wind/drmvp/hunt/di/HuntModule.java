package com.wind.drmvp.hunt.di;

import android.content.Context;

import com.wind.base.di.annotation.ActivityScope;
import com.wind.base.http.loader.PageLoader;
import com.wind.base.http.page.IPage;
import com.wind.base.usecase.Usecase;
import com.wind.data.Repository;
import com.wind.data.base.cache.ICache;
import com.wind.data.hunt.HuntPage;
import com.wind.data.hunt.HuntPageLoader;
import com.wind.data.hunt.api.HuntApi;
import com.wind.data.hunt.cache.HuntCache;
import com.wind.data.hunt.datastore.HuntDataStoreFactory;
import com.wind.data.hunt.repository.HuntRepository;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.domain.hunt.interactor.HuntPageUsecase;
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

   /* @ActivityScope
    @Provides
    public Usecase<HuntRequest,HuntResponse> provideUsecase(HuntUsecase usecase){
        return usecase;
    }
*/


    @ActivityScope
    @Provides
    public IPage<HuntRequest,HuntResponse> providePage(Retrofit retrofit){
        //IPageApi api=retrofit.create(IPageApi.class);
        HuntApi api=new HuntApi(retrofit);
        return new HuntPage(api);
    }
   @ActivityScope
   @Provides
   public PageLoader<HuntRequest,HuntResponse> providePageLoader( IPage<HuntRequest,HuntResponse> page){
       return new HuntPageLoader(page);
   }
    @ActivityScope
    @Provides
    public Usecase<HuntRequest,HuntResponse> provideUsecase(HuntPageUsecase usecase){
        return usecase;
    }


}
