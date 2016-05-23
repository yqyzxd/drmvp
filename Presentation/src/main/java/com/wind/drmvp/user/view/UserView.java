package com.wind.drmvp.user.view;

import com.wind.base.mvp.view.LoadingMvpView;
import com.wind.data.login.response.LoginResponse;

/**
 * Created by wind on 16/5/23.
 */
public interface UserView extends LoadingMvpView {
    void getLoginUser();
    void getLoginUserReturn(LoginResponse response);
}
