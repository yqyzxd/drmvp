package com.wind.drmvp.hunt.di;

import com.wind.base.di.BaseMvpComponent;
import com.wind.base.di.annotation.ActivityScope;
import com.wind.drmvp.hunt.mvp.presenter.LikePresenter;
import com.wind.drmvp.hunt.mvp.view.LikeView;
import com.wind.drmvp.hunt.mvp.view.impl.LikeMvpLayout;

import dagger.Subcomponent;

/**
 * Created by wind on 16/9/12.
 */

@ActivityScope
@Subcomponent(modules = LikeModule.class)
public interface LikeComponent extends BaseMvpComponent<LikeView,LikePresenter> {

    void inject(LikeMvpLayout likeMvpLayout);

}
