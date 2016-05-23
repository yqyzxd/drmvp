package com.wind.drmvp.login.mvp.presenter;

import com.wind.base.mvp.presenter.MvpBasePresenter;
import com.wind.data.base.BaseResponse;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.domain.Usecase;
import com.wind.drmvp.base.App;
import com.wind.drmvp.login.mvp.view.LoginView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by wind on 16/5/18.
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {

    Usecase<LoginRequest,LoginResponse> usecase;
    @Inject
    public LoginPresenter(Usecase<LoginRequest,LoginResponse> usecase){
        this.usecase=usecase;
    }


    public void login(LoginRequest loginRequest) {
       this.usecase.execute(loginRequest,new LoginSubscriber());
    }



    private final class LoginSubscriber extends Subscriber<LoginResponse> {

        @Override public void onCompleted() {
        }

        @Override public void onError(Throwable e) {

            showNetworkError(App.get());
        }

        @Override public void onNext(LoginResponse response) {
            if (isViewAttached()){
                if (response.getErrCode()== BaseResponse.CODE_SUCCESS){
                    getView().loginSuccess(response);
                }else {
                    getView().loginError(response.getErrMsg());
                }
            }
        }
    }
}
