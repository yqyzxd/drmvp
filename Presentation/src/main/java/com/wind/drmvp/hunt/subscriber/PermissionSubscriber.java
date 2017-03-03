package com.wind.drmvp.hunt.subscriber;

import com.wind.base.response.BaseResponse;
import com.wind.data.hunt.response.PermissionResponse;
import com.wind.drmvp.hunt.mvp.view.PermissionView;

import rx.Observer;

public class PermissionSubscriber implements Observer<PermissionResponse> {
    PermissionView permissionView;

    public PermissionSubscriber(PermissionView permissionView) {
        this.permissionView = permissionView;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(PermissionResponse response) {
            if (response.getErrCode() == BaseResponse.CODE_SUCCESS) {
                permissionView.checkPermisionSuccess();
            } else {
            }

    }
}