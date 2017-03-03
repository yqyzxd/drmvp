package com.wind.drmvp.hunt.subscriber;

import com.wind.base.response.BaseResponse;
import com.wind.data.hunt.response.LikeResponse;
import com.wind.drmvp.hunt.mvp.view.LikeView;

import rx.Observer;

public class LikeSubscriber implements Observer<LikeResponse> {
    LikeView likeView;
    public LikeSubscriber(LikeView likeView) {
        this.likeView = likeView;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(LikeResponse response) {
        if (response.getErrCode() == BaseResponse.CODE_SUCCESS) {
            likeView.likeUnLikeSuccess();
        } else {
            likeView.likeUnLikeFailed(response.getErrMsg());
        }
    }
}