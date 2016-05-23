package com.wind.base.di;

import com.wind.base.mvp.presenter.MvpPrensenter;
import com.wind.base.mvp.view.MvpView;

/**
 * Created by wind on 16/5/18.
 */
public interface BaseMvpComponent<V extends MvpView,P extends MvpPrensenter<V>> extends DaggerComponent {

    P presenter();
}
