package com.wind.drmvp.hunt.di;

import com.wind.base.di.BaseMvpComponent;
import com.wind.base.di.annotation.ActivityScope;
import com.wind.drmvp.hunt.mvp.presenter.UploadPhotosPresenter;
import com.wind.drmvp.hunt.mvp.view.UploadPhotosView;
import com.wind.drmvp.hunt.mvp.view.impl.UploadPhotosFragment;

import dagger.Subcomponent;

/**
 * Created by wind on 2017/3/1.
 */
@ActivityScope
@Subcomponent(modules = UploadPhotosModule.class)
public interface UploadPhotosComponent extends BaseMvpComponent<UploadPhotosView,UploadPhotosPresenter> {
    void inject(UploadPhotosFragment fragment);
}
