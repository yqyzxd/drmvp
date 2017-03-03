package com.wind.drmvp.hunt.mvp.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wind.base.mvp.view.DaggerMvpFragment;
import com.wind.base.request.BaseRequest;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.drmvp.R;
import com.wind.drmvp.base.App;
import com.wind.drmvp.hunt.activity.UploadPhotosActivity;
import com.wind.drmvp.hunt.adapter.HuntUsersAdapter;
import com.wind.drmvp.hunt.di.HuntComponent;
import com.wind.drmvp.hunt.mvp.presenter.HuntPresenter;
import com.wind.drmvp.hunt.mvp.view.HuntView;

import butterknife.Bind;

/**
 * Created by wind on 16/5/20.
 */
public class HuntFragment extends DaggerMvpFragment<HuntView,HuntPresenter,HuntComponent> implements
        HuntView{
    @Bind(R.id.contentView)
    PullToRefreshListView contentView;

    @Bind(R.id.empty_view)
    View empty_view;
    @Override
    public HuntPresenter createPresenter() {
        return getComponent().presenter();
    }

    @Override
    public int getLayoutRes() {
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
        contentView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), UploadPhotosActivity.class);

                startActivity(intent);
            }
        });
        contentView.setMode(PullToRefreshBase.Mode.BOTH);
        contentView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //getFirstPage();
                getUserList(mResponse,true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                getUserList(mResponse,false);
            }
        });
        getLoginUser();
    }

    private void getUserList(LoginResponse response,boolean isFirstPage) {


        //presenter.getUserList(buildRequest(response));
        presenter.execute(buildRequest(response,isFirstPage));
    }




    private HuntRequest buildRequest(LoginResponse response,boolean isFirstPage) {
        HuntRequest request=new HuntRequest();
        request.setToken(response.getLoginUser().getBaseUserInfo().getToken());
        //request.setPage(1);
        request.setCount(10);
        request.setFirstPage(isFirstPage);
        request.setLoadFrom(BaseRequest.LOAD_FROM_WEB);
        return request;
    }

    @Override
    public void showUsers(HuntResponse response) {
        empty_view.setVisibility(View.GONE);
        if (response.isFirstPage()){
            mAdapter.replaceAll(response.getHunt().getUserInfos());
        }else
            mAdapter.addAll(response.getHunt().getUserInfos());


        contentView.onRefreshComplete();
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
        presenter.execute(new LoginRequest());
    }

    private LoginResponse mResponse;
    @Override
    public void getLoginUserReturn(LoginResponse response) {
        this.mResponse=response;
        App.get().setLoginUser(response.getLoginUser());
        getUserList(response,true);
    }

    public static Fragment getInstance() {

        HuntFragment fragment=new HuntFragment();
        return fragment;
    }



}
