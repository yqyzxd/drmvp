package com.wind.drmvp.login.mvp.view;

import com.wind.base.mvp.view.LoadingMvpView;
import com.wind.data.login.response.LoginResponse;

/**
 * Created by wind on 16/5/18.
 */
public interface LoginView extends LoadingMvpView{

    void loginSuccess(LoginResponse response);

    void loginError(String msg);
}
