package com.wind.drmvp.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.wind.data.base.BaseRequest;
import com.wind.domain.UsecaseManager;

/**
 * Created by wind on 16/9/11.
 */

public abstract class ExecutePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    protected UsecaseManager manager;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        manager=new UsecaseManager();
    }

    public void execute(BaseRequest request) {
        manager.execute(request);
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            cancelSubscription();
        }
    }

    private void cancelSubscription() {
        manager.cancelSubscription();
    }
}
