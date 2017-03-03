package com.wind.drmvp.login.subscriber;

import android.util.Log;

import com.wind.base.response.BaseResponse;
import com.wind.data.login.response.LoginResponse;
import com.wind.drmvp.login.mvp.view.LoginView;

import rx.Observer;

public class LoginSubscriber implements Observer<LoginResponse> {
    public static final String TAG="LoginSubscriber";
        private LoginView loginView;
        public LoginSubscriber(LoginView loginView){
            this.loginView=loginView;
        }

        @Override public void onCompleted() {
        }

        @Override public void onError(Throwable e) {
            e.printStackTrace();
            Log.e(TAG,"onError");
            //presenter.showNetworkError(App.get());
        }

        @Override public void onNext(LoginResponse response) {

                if (response.getErrCode()== BaseResponse.CODE_SUCCESS){
                    loginView.loginSuccess(response);
                }else {
                    loginView.loginError(response.getErrMsg());
                }

        }
    }