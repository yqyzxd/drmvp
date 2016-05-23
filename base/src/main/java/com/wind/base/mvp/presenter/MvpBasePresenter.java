package com.wind.base.mvp.presenter;

import android.content.Context;

import com.wind.base.R;
import com.wind.base.mvp.view.MvpView;
import com.wind.base.utils.ToastUtil;

import java.lang.ref.WeakReference;

/**
 * Created by wind on 16/5/18.
 */
public abstract class MvpBasePresenter<V extends MvpView> implements MvpPrensenter<V>{
    private WeakReference<V> mvpViewRef;
    @Override
    public void attachView(V mvpView) {
        this.mvpViewRef=new WeakReference<V>(mvpView);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (!retainInstance){
            if (mvpViewRef != null) {
                mvpViewRef.clear();
            }
        }
    }

    public boolean isViewAttached(){
        return mvpViewRef!=null&&mvpViewRef.get()!=null;
    }

    @Override
    public V getView() {
        return mvpViewRef.get();
    }
    public void showNetworkError(Context context){
        ToastUtil.showToast(context,context.getString(R.string.error_network));
    }

}
