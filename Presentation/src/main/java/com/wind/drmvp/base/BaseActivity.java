package com.wind.drmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wind.drmvp.R;
import com.wind.drmvp.base.di.AppComponent;

/**
 * Created by wind on 16/5/18.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);

    }

    /**
     * Initialize dependency injector.
     */
    protected abstract void initializeInjector();

    protected AppComponent getApplicationComponent() {
        return ((App)(getApplication())).appComponent();
    }

    public void replaceFragment(Fragment fragment){
        if (fragment!=null){
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction transaction=fm.beginTransaction();

            transaction.replace(R.id.fl_fragment_container,fragment);
            transaction.commitAllowingStateLoss();
        }

    }
}
