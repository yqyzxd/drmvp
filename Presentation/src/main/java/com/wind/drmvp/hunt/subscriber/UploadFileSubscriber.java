package com.wind.drmvp.hunt.subscriber;

import android.util.Log;

import com.wind.base.response.BaseResponse;
import com.wind.base.response.UploadFileResponse;
import com.wind.drmvp.hunt.mvp.view.UploadPhotosView;

import rx.Observer;

/**
 * Created by wind on 2017/3/1.
 */

public class UploadFileSubscriber implements Observer<UploadFileResponse> {
    private String TAG="UploadFileSubscriber";
    private UploadPhotosView mView;
    public UploadFileSubscriber(UploadPhotosView view){
        mView=view;
    }
    @Override
    public void onCompleted() {
        Log.e(TAG,"onCompleted");
        mView.onUploadSuccess();
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG,"onError");
    }

    @Override
    public void onNext(UploadFileResponse response) {
        Log.e(TAG,"onNext:");
        if (response.getErrCode() == BaseResponse.CODE_SUCCESS) {

        }
    }
}
