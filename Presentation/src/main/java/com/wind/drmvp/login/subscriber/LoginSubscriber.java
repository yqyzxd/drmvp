package com.wind.drmvp.login.subscriber;

import com.wind.data.base.BaseResponse;
import com.wind.data.login.response.LoginResponse;
import com.wind.drmvp.login.mvp.view.LoginView;

import rx.Observer;

public class LoginSubscriber implements Observer<LoginResponse> {
        private LoginView loginView;
        public LoginSubscriber(LoginView loginView){
            this.loginView=loginView;
        }

        @Override public void onCompleted() {
        }

        @Override public void onError(Throwable e) {

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