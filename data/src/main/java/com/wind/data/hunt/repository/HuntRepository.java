package com.wind.data.hunt.repository;

import com.wind.data.DataStore;
import com.wind.data.Repository;
import com.wind.data.hunt.datastore.HuntDataStoreFactory;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by wind on 16/5/20.
 */
public class HuntRepository implements Repository<HuntRequest,HuntResponse> {
    private HuntDataStoreFactory factory;
    @Inject
    public HuntRepository(HuntDataStoreFactory factory){
        this.factory=factory;
    }
    @Override
    public Observable<HuntResponse> get(HuntRequest request) {

        DataStore dataStore= factory.create(request);
        return dataStore.get(request);
    }
}
