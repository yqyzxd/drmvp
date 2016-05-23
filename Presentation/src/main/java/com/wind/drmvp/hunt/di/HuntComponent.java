package com.wind.drmvp.hunt.di;

import com.wind.base.di.BaseMvpComponent;
import com.wind.base.di.annotation.ActivityScope;
import com.wind.drmvp.hunt.mvp.presenter.HuntPresenter;
import com.wind.drmvp.hunt.mvp.view.HuntView;
import com.wind.drmvp.hunt.mvp.view.impl.HuntFragment;

import dagger.Subcomponent;

/**
 * Created by wind on 16/5/20.
 */
@ActivityScope
@Subcomponent(modules = HuntModule.class)
public interface HuntComponent extends BaseMvpComponent<HuntView,HuntPresenter> {

    void inject(HuntFragment huntFragment);
}
