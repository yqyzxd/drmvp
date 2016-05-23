package com.wind.drmvp.login.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.wind.base.di.HasComponent;
import com.wind.drmvp.R;
import com.wind.drmvp.base.App;
import com.wind.drmvp.base.BaseActivity;
import com.wind.drmvp.login.di.LoginComponent;
import com.wind.drmvp.login.di.LoginModule;
import com.wind.drmvp.login.mvp.view.impl.LoginFragment;

/**
 * Created by wind on 16/5/18.
 */
public class LoginActivity extends BaseActivity implements HasComponent<LoginComponent>{
    private LoginComponent mComponent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        replaceFragment(LoginFragment.getInstance());
    }

    @Override
    protected void initializeInjector() {
        mComponent= App.get()
                .appComponent()
                .plus(new LoginModule(this));
        Log.e("LoginActivity","mComponent==null"+(mComponent==null));
    }


    @Override
    public LoginComponent getComponent() {
        return mComponent;
    }
}
