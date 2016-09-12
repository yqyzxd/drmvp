package com.wind.drmvp.hunt.subscriber;

import android.util.Log;

import com.wind.data.base.BaseResponse;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.drmvp.hunt.mvp.view.HuntView;

import rx.Observer;

public class HuntSubscriber implements Observer<HuntResponse> {
    HuntView huntView;

    public HuntSubscriber(HuntView huntView) {
        this.huntView = huntView;
    }

    @Override
    public void onCompleted() {
        Log.e("HuntSubscriber","onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.e("HuntSubscriber","onError");
       // presenter.showNetworkError(App.get());
    }

    @Override
    public void onNext(HuntResponse response) {
        Log.e("HuntSubscriber","onNext");
            if (response.getErrCode() == BaseResponse.CODE_SUCCESS) {
                huntView.showUsers(response);
            } else {
                huntView.showError(response.getErrMsg());
            }

    }
}