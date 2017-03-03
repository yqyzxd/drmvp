package com.wind.drmvp.hunt.activity;

import android.os.Bundle;

import com.wind.base.di.HasComponent;
import com.wind.drmvp.R;
import com.wind.drmvp.base.App;
import com.wind.drmvp.base.BaseActivity;
import com.wind.drmvp.hunt.di.UploadPhotosComponent;
import com.wind.drmvp.hunt.di.UploadPhotosModule;
import com.wind.drmvp.hunt.mvp.view.impl.UploadPhotosFragment;

/**
 * Created by sh on 2015/10/9.
 */
public class UploadPhotosActivity extends BaseActivity implements HasComponent<UploadPhotosComponent> {

    private UploadPhotosComponent mComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        replaceFragment(UploadPhotosFragment.getInstance());
    }

    @Override
    protected void initializeInjector() {
        mComponent = App.get()
                .appComponent()
                .plus(new UploadPhotosModule(this));
    }

    @Override
    public UploadPhotosComponent getComponent() {
        return mComponent;
    }

}
