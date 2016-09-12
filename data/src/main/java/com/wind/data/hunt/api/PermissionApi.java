package com.wind.data.hunt.api;

import com.wind.data.hunt.response.PermissionResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wind on 16/9/12.
 */

public interface PermissionApi {

    @FormUrlEncoded
    @POST("user/power")
    Observable<PermissionResponse> checkPermission(@FieldMap Map<String,String> fieldMap);
}
