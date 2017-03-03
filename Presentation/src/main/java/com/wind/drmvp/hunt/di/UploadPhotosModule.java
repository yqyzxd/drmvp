package com.wind.drmvp.hunt.di;

import android.app.Activity;

import com.wind.base.di.annotation.ActivityScope;
import com.wind.base.request.UploadFileRequest;
import com.wind.base.response.UploadFileResponse;
import com.wind.base.usecase.UploadFileUsecase;
import com.wind.base.usecase.Usecase;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class UploadPhotosModule {

    private Activity mActivity;

    public UploadPhotosModule(Activity activity) {
        this.mActivity = activity;
    }


    @ActivityScope
    @Provides
    public Usecase<UploadFileRequest, UploadFileResponse> provideUsecase(Retrofit retrofit) {
        return new UploadFileUsecase(retrofit);
    }
}
