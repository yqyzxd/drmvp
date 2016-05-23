package com.wind.base.mvp.presenter;

import com.wind.base.mvp.view.MvpView;

/**
 * Created by wind on 16/5/18.
 */
public interface MvpPrensenter<V extends MvpView> {

    void attachView(V mvpView);
    void detachView(boolean retainInstance);

    V getView();
}
