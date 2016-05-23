package com.wind.data.hunt.datastore;

import com.wind.data.DataStore;
import com.wind.data.base.BaseRequest;
import com.wind.data.base.cache.ICache;
import com.wind.data.hunt.api.HuntApi;
import com.wind.data.hunt.request.HuntRequest;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by wind on 16/5/20.
 */
public class HuntDataStoreFactory {
    private ICache cache;
    private Retrofit retrofit;
    @Inject
    public HuntDataStoreFactory(Retrofit retrofit,ICache cache){
        this.cache=cache;
        this.retrofit=retrofit;
    }

    public DataStore create(HuntRequest request) {
        DataStore dataStore=null;
        int loadFrom=request.getLoadFrom();

        if (BaseRequest.LOAD_FROM_WEB==loadFrom){
            dataStore = createCloudDataStore(retrofit);
            return dataStore;
        }
        if (BaseRequest.LOAD_FROM_LOCAL==loadFrom ||!this.cache.isExpired() && this.cache.isCached()) {
            //LogUtils.e("DiskHuntUserDataStore","use DiskHuntUserDataStore");
            //dataStore = new DiskUserDataStore();
        } else {
            //LogUtils.e("CloudDataStore","use CloudDataStore");
            dataStore = createCloudDataStore(retrofit);
        }
        return dataStore;


    }

    private DataStore createCloudDataStore(Retrofit retrofit) {
        HuntApi api=retrofit.create(HuntApi.class);
        return new HuntCloudDataStore(api);
    }


}
