package com.wind.domain.hunt.interactor;

import android.text.TextUtils;

import com.wind.data.hunt.api.PermissionApi;
import com.wind.data.hunt.request.PermissionRequest;
import com.wind.data.hunt.response.PermissionResponse;
import com.wind.base.usecase.Usecase;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by wind on 16/9/12.
 */

public class PermissionUsecase extends Usecase<PermissionRequest,PermissionResponse> {

    private Retrofit retrofit;
    @Inject
    public PermissionUsecase(Retrofit retrofit){
        this.retrofit=retrofit;
    }
    @Override
    protected Observable<PermissionResponse> buildUsecaseObservable(PermissionRequest request) {
        PermissionApi api=retrofit.create(PermissionApi.class);

        return api.checkPermission(buildParams(request));
    }

    private Map<String, String> buildParams(PermissionRequest request) {
        Map<String,String> params=new HashMap<>();
        params.put("token",request.getToken());
        params.put("version","1.61");
        params.put("power_type",request.getPowerType());
        if (!TextUtils.isEmpty(request.getToUid()))
            params.put("to_uid",request.getToUid());

        return params;
    }
}
