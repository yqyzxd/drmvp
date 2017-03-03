package com.wind.domain.hunt.interactor;

import com.wind.data.hunt.api.LikeApi;
import com.wind.data.hunt.request.LikeRequest;
import com.wind.data.hunt.response.LikeResponse;
import com.wind.base.usecase.Usecase;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by wind on 16/9/12.
 */

public class LikeUsecase extends Usecase<LikeRequest,LikeResponse> {


    private Retrofit retrofit;
    @Inject
    public LikeUsecase(Retrofit retrofit){
        this.retrofit=retrofit;
    }
    @Override
    protected Observable<LikeResponse> buildUsecaseObservable(LikeRequest request) {
        LikeApi api=retrofit.create(LikeApi.class);
        Map<String,String> params=buildParams(request);
        if (request.isLike()){
            return api.like(params);
        }else {
            return api.unlike(params);
        }
    }

    private Map<String,String> buildParams(LikeRequest request) {
        Map<String,String> map=new HashMap<>();
        map.put("token",request.getToken());
        map.put("to_uid",request.getUid());
        map.put("version","1.61");
        return map;
    }
}
