package com.wind.drmvp.login.di;

import com.wind.base.di.annotation.ActivityScope;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.domain.Usecase;
import com.wind.domain.login.interactor.LoginUsecase;
import com.wind.drmvp.login.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wind on 16/5/18.
 */
@Module
public class LoginModule {
    private LoginActivity activity;
    public LoginModule(LoginActivity activity){
        this.activity=activity;
    }

    @Provides
    @ActivityScope
    public LoginActivity activity(){
        return this.activity;
    }

    @ActivityScope
    @Provides
    public Usecase<LoginRequest,LoginResponse> provideUsecase(LoginUsecase loginUsecase){
        return loginUsecase;
    }
   /* @ActivityScope
    @Provides
    public LoginUserDbDataStore provideLoginUserDbDataStore(BriteDatabase database, Gson gson){
        return new LoginUserDbDataStore(database,gson);
    }*/
}
