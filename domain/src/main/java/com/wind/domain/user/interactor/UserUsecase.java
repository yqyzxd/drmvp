package com.wind.domain.user.interactor;

import com.wind.data.login.datastore.LoginUserDbDataStore;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.base.usecase.Usecase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by wind on 16/5/23.
 */
public class UserUsecase extends Usecase<LoginRequest,LoginResponse> {

    private LoginUserDbDataStore dataStore;
    @Inject
    public UserUsecase(LoginUserDbDataStore dataStore){
        this.dataStore=dataStore;
    }


    @Override
    protected Observable<LoginResponse> buildUsecaseObservable(LoginRequest request) {

        return dataStore.getLoginUser();
    }
}
