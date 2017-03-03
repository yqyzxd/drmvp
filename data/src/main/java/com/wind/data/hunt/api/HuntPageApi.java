package com.wind.data.hunt.api;

import com.wind.data.hunt.response.HuntResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wind on 2017/2/28.
 */

public interface HuntPageApi {
    @FormUrlEncoded
    @POST("user/user_list")
    Observable<HuntResponse> get(@FieldMap Map<String,String> fieldMap);


}
