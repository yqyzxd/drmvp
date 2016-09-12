package com.wind.drmvp.login.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.wind.base.mvp.view.DaggerMvpFragment;
import com.wind.base.utils.ToastUtil;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.drmvp.R;
import com.wind.drmvp.login.di.LoginComponent;
import com.wind.drmvp.login.mvp.presenter.LoginPresenter;
import com.wind.drmvp.login.mvp.view.LoginView;
import com.wind.drmvp.utils.Navigator;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wind on 16/5/18.
 */
public class LoginFragment extends DaggerMvpFragment<LoginView,LoginPresenter,LoginComponent> implements LoginView{
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_password;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public LoginPresenter createPresenter() {
        return getComponent().presenter();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.login_fragment;
    }


    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    public static Fragment getInstance() {

        return new LoginFragment();
    }


    @OnClick(R.id.tv_login)
    public void login(View v){
        String username=et_username.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        LoginRequest request=new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
       // presenter.login(request);
        presenter.execute(request);
    }

    @Override
    public void loginSuccess(LoginResponse response) {
        ToastUtil.showToast(getActivity(),"登录成功");
        Navigator.startHuntActivity(getActivity());
        //Log.e("LoginFragment","loginSuccess");
    }

    @Override
    public void loginError(String msg) {
        ToastUtil.showToast(getActivity(),msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }
}
