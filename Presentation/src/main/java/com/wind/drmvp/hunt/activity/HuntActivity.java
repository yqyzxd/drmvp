package com.wind.drmvp.hunt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wind.base.di.HasComponent;
import com.wind.drmvp.R;
import com.wind.drmvp.base.App;
import com.wind.drmvp.base.BaseActivity;
import com.wind.drmvp.hunt.di.HuntComponent;
import com.wind.drmvp.hunt.di.HuntModule;
import com.wind.drmvp.hunt.mvp.view.impl.HuntFragment;

/**
 * Created by wind on 16/5/20.
 */
public class HuntActivity extends BaseActivity implements HasComponent<HuntComponent> {
    private HuntComponent mComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        replaceFragment(HuntFragment.getInstance());
    }

    @Override
    protected void initializeInjector() {
        mComponent= App.get()
                .appComponent()
                .plus(new HuntModule(this));
    }

    @Override
    public HuntComponent getComponent() {
        return mComponent;
    }
}
