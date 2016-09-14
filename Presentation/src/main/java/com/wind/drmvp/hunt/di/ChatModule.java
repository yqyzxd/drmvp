package com.wind.drmvp.hunt.di;

import com.wind.base.di.annotation.ActivityScope;
import com.wind.data.hunt.request.PermissionRequest;
import com.wind.data.hunt.response.PermissionResponse;
import com.wind.domain.Usecase;
import com.wind.domain.hunt.interactor.PermissionUsecase;
import com.wind.drmvp.hunt.mvp.view.impl.ChatMvpLayout;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wind on 16/9/12.
 */
@Module
public class ChatModule {

    private ChatMvpLayout layout;
    public ChatModule(ChatMvpLayout layout){
        this.layout=layout;
    }



    @ActivityScope
    @Provides
    public Usecase<PermissionRequest,PermissionResponse>  providePermissionUsecase(PermissionUsecase usecase){
        return usecase;
    }
}
