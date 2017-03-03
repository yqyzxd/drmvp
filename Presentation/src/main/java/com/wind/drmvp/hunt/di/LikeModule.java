package com.wind.drmvp.hunt.di;

import com.wind.base.di.annotation.ActivityScope;
import com.wind.data.hunt.request.LikeRequest;
import com.wind.data.hunt.request.PermissionRequest;
import com.wind.data.hunt.response.LikeResponse;
import com.wind.data.hunt.response.PermissionResponse;
import com.wind.base.usecase.Usecase;
import com.wind.domain.hunt.interactor.LikeUsecase;
import com.wind.domain.hunt.interactor.PermissionUsecase;
import com.wind.drmvp.hunt.mvp.view.impl.LikeMvpLayout;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wind on 16/9/12.
 */
@Module
public class LikeModule {

    private LikeMvpLayout layout;
    public LikeModule(LikeMvpLayout layout){
        this.layout=layout;
    }

    @ActivityScope
    @Provides
    public Usecase<LikeRequest,LikeResponse>  provideLikeUsecase(LikeUsecase usecase){
        return usecase;
    }

    @ActivityScope
    @Provides
    public Usecase<PermissionRequest,PermissionResponse>  providePermissionUsecase(PermissionUsecase usecase){
        return usecase;
    }
}
