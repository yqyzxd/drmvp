package com.wind.data.hunt.datastore;

import com.wind.data.hunt.api.HuntApi;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by wind on 16/5/20.
 */
public class HuntCloudDataStore implements HuntDataStore {

    private HuntApi api;
    public HuntCloudDataStore(HuntApi api){
        this.api=api;
    }


    @Override
    public Observable<HuntResponse> get(HuntRequest request) {
        return api.get(buidlParamMap(request));
    }

    private Map<String, String> buidlParamMap(HuntRequest request) {
        Map<String,String> map=new HashMap<>();
        map.put("token", request.getToken());
        map.put("page", String.valueOf(request.getPage()));
        map.put("count", String.valueOf(request.getCount()));
        map.put("version", "141");
        map.put("order_rand", "0");
        map.put("load_from", request.getLoadFrom() + "");
        return map;
    }
}
