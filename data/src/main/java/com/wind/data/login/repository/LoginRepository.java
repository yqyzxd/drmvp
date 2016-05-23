package com.wind.data.login.repository;

import com.wind.data.Repository;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;

import rx.Observable;

/**
 * Created by wind on 16/5/19.
 */
public class LoginRepository implements Repository<LoginRequest,LoginResponse> {


    @Override
    public Observable<LoginResponse> get(LoginRequest request) {
        return null;
    }
}
