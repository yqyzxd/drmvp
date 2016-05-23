package com.wind.data.login.api;

import com.wind.data.login.response.LoginResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wind on 16/5/19.
 */
public interface LoginApi  {
    /*@POST("public/login")
    Observable<LoginResponse> login(@Body LoginRequest request);*/
    @FormUrlEncoded
    @POST("public/login")
    Observable<LoginResponse> login(@FieldMap Map<String,String> fieldMap);
}
