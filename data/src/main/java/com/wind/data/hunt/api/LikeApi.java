package com.wind.data.hunt.api;

import com.wind.data.hunt.response.LikeResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wind on 16/9/12.
 */

public interface LikeApi {

    @FormUrlEncoded
    @POST("user/like")
    Observable<LikeResponse> like(@FieldMap Map<String,String> fieldMap);

    @FormUrlEncoded
    @POST("user/unlike")
    Observable<LikeResponse> unlike(@FieldMap Map<String,String> fieldMap);
}
