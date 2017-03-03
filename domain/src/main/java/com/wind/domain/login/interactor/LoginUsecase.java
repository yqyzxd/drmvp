package com.wind.domain.login.interactor;

import com.wind.base.response.BaseResponse;
import com.wind.data.login.api.LoginApi;
import com.wind.data.login.datastore.LoginUserDbDataStore;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.base.usecase.Usecase;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wind on 16/5/19.
 */
public class LoginUsecase extends Usecase<LoginRequest,LoginResponse> {

    LoginUserDbDataStore dataStore;
    Retrofit retrofit;
    @Inject
    public LoginUsecase(Retrofit retrofit,LoginUserDbDataStore dataStore){
        this.dataStore=dataStore;
        this.retrofit=retrofit;
    }

    Action1<LoginResponse> saveAction=new Action1<LoginResponse>() {
        @Override
        public void call(LoginResponse response) {
            if (response.getErrCode()== BaseResponse.CODE_SUCCESS){
                //保存到数据库
                dataStore.putLoginUser(response);
            }
        }
    };
    @Override
    protected Observable<LoginResponse> buildUsecaseObservable(LoginRequest request) {

        LoginApi loginApi=retrofit.create(LoginApi.class);

        return loginApi.login(buildParamMap(request)).doOnNext(saveAction);
    }

    private Map<String,String> buildParamMap(LoginRequest request) {

        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("country_code","86");
        paramMap.put(LoginRequest.FIELD_USERNAME,request.getUsername());
        paramMap.put(LoginRequest.FIELD_PASSWORD,request.getPassword());
        paramMap.put("version","1.41");
        return paramMap;
    }
}
