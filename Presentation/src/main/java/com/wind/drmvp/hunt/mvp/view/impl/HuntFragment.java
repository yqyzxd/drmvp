package com.wind.drmvp.hunt.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wind.base.mvp.view.MvpFragment;
import com.wind.data.base.BaseRequest;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.data.login.response.LoginResponse;
import com.wind.drmvp.R;
import com.wind.drmvp.hunt.adapter.HuntUsersAdapter;
import com.wind.drmvp.hunt.di.HuntComponent;
import com.wind.drmvp.hunt.mvp.presenter.HuntPresenter;
import com.wind.drmvp.hunt.mvp.view.HuntView;

import butterknife.Bind;

/**
 * Created by wind on 16/5/20.
 */
public class HuntFragment extends MvpFragment<HuntView,HuntPresenter,HuntComponent> implements HuntView {
    @Bind(R.id.contentView)
    PullToRefreshListView contentView;

    @Bind(R.id.empty_view)
    View empty_view;
    @Override
    protected HuntPresenter createPresenter() {
        return getComponent().presenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.hunt_fragment;
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    HuntUsersAdapter mAdapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mAdapter=new HuntUsersAdapter(getActivity(),R.layout.hunt_item_user);
        contentView.setAdapter(mAdapter);


        getLoginUser();
    }

    private void getUserList(LoginResponse response) {


        presenter.getUserList(buildRequest(response));
    }




    private HuntRequest buildRequest(LoginResponse response) {
        HuntRequest request=new HuntRequest();

        request.setToken(response.getLoginUser().getBaseUserInfo().getToken());
        request.setPage(1);
        request.setCount(20);
        request.setLoadFrom(BaseRequest.LOAD_FROM_WEB);
        return request;
    }

    @Override
    public void showUsers(HuntResponse response) {
        empty_view.setVisibility(View.GONE);
        mAdapter.addAll(response.getHunt().getUserInfos());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void getLoginUser() {
        presenter.getLoginUser();
    }

    @Override
    public void getLoginUserReturn(LoginResponse response) {

        getUserList(response);
    }

    public static Fragment getInstance() {

        HuntFragment fragment=new HuntFragment();
        return fragment;
    }
}
