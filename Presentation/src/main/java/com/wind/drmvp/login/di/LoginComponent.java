package com.wind.drmvp.login.di;

import com.wind.base.di.BaseMvpComponent;
import com.wind.base.di.annotation.ActivityScope;
import com.wind.drmvp.login.mvp.presenter.LoginPresenter;
import com.wind.drmvp.login.mvp.view.LoginView;
import com.wind.drmvp.login.mvp.view.impl.LoginFragment;

import dagger.Subcomponent;

/**
 * Created by wind on 16/5/18.
 */
@ActivityScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent extends BaseMvpComponent<LoginView,LoginPresenter> {
    void inject(LoginFragment fragment);
}
