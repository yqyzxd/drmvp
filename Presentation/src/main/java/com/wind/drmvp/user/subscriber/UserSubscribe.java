package com.wind.drmvp.user.subscriber;

import com.wind.data.base.BaseResponse;
import com.wind.data.login.response.LoginResponse;
import com.wind.drmvp.user.view.UserView;

import rx.Subscriber;

public class UserSubscribe extends Subscriber<LoginResponse> {
    private UserView userView;

    public UserSubscribe(UserView userView) {
        this.userView = userView;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(LoginResponse response) {
        if (response.getErrCode() == BaseResponse.CODE_SUCCESS) {
            userView.getLoginUserReturn(response);
        }
    }
}