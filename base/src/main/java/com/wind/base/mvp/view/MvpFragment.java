package com.wind.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wind.base.di.BaseDIFragment;
import com.wind.base.di.DaggerComponent;
import com.wind.base.mvp.presenter.MvpPrensenter;

import butterknife.ButterKnife;

/**
 * Created by wind on 16/5/18.
 */
public abstract class MvpFragment<V extends MvpView,P extends MvpPrensenter<V>,C extends DaggerComponent> extends BaseDIFragment<C> implements MvpView{
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(getLayoutRes(),container,false);

        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=createPresenter();
        presenter.attachView((V)this);
        ButterKnife.bind(this,view);


    }

    protected abstract P createPresenter();

    protected abstract int getLayoutRes();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView(getRetainInstance());
    }
}
