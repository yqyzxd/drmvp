package com.wind.drmvp.hunt.mvp.presenter;

import com.wind.base.usecase.UploadFileUsecase;
import com.wind.base.usecase.UsecaseCompoment;
import com.wind.drmvp.base.ExecutePresenter;
import com.wind.drmvp.hunt.mvp.view.UploadPhotosView;
import com.wind.drmvp.hunt.subscriber.UploadFileSubscriber;

import javax.inject.Inject;

/**
 * Created by wind on 2017/3/1.
 */

public class UploadPhotosPresenter extends ExecutePresenter<UploadPhotosView> {

    private UploadFileUsecase mUploadFileUsecase;
    @Inject
    public UploadPhotosPresenter(UploadFileUsecase usecase){
        this.mUploadFileUsecase=usecase;
    }

    @Override
    public void attachView(UploadPhotosView mvpView) {
        super.attachView(mvpView);
        manager
                .addUsercaseCompoment(new UsecaseCompoment(new UploadFileSubscriber(getView()),mUploadFileUsecase));


    }
}
