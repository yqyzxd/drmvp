package com.wind.drmvp.user.subscriber;

import android.util.Log;

import com.wind.base.response.BaseResponse;
import com.wind.data.login.response.LoginResponse;
import com.wind.drmvp.user.view.UserView;

import rx.Subscriber;

public class UserSubscribe extends Subscriber<LoginResponse> {
    public static final String TAG="UserSubscribe";
    private UserView userView;

    public UserSubscribe(UserView userView) {
        this.userView = userView;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG,"onError");
    }

    @Override
    public void onNext(LoginResponse response) {
        if (response.getErrCode() == BaseResponse.CODE_SUCCESS) {
            userView.getLoginUserReturn(response);
        }
    }
}