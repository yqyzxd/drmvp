package com.wind.data.hunt.datastore;

import com.wind.data.hunt.api.HuntApi;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wind on 16/5/20.
 */
public class HuntCloudDataStore implements HuntDataStore {

    private HuntApi api;

    private AtomicInteger olderPageOffset=new AtomicInteger(0);
    private int page;
    private LinkedBlockingQueue<Integer> olderFailedButRetryLater=new LinkedBlockingQueue<>();
    public HuntCloudDataStore(HuntApi api){
        this.api=api;
    }



    @Override
    public Observable<HuntResponse> get(HuntRequest request) {

        if (olderFailedButRetryLater.isEmpty()){
            page=olderPageOffset.addAndGet(1);
        }else {
            page=olderFailedButRetryLater.poll();
        }

        final boolean isFirstPage=request.isFirstPage();

        Observable<HuntResponse> observable=api.get(buidlParamMap(request)).map(new Func1<HuntResponse, HuntResponse>() {
            @Override
            public HuntResponse call(HuntResponse huntResponse) {
                huntResponse.setFirstPage(isFirstPage);
                return huntResponse;
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                olderFailedButRetryLater.add(page);
            }
        });

        return observable;
    }

    private Map<String, String> buidlParamMap(HuntRequest request) {
        Map<String,String> map=new HashMap<>();
        map.put("token", request.getToken());
        map.put("page", String.valueOf(page));
        map.put("count", String.valueOf(request.getCount()));
        map.put("version", "1.61");
        map.put("order_rand", "0");
        map.put("load_from", request.getLoadFrom() + "");
        return map;
    }

    public void setFirstPage(boolean isFirstPage) {
        if (isFirstPage){
            olderPageOffset.set(0);
            olderFailedButRetryLater.clear();
        }
    }
}
