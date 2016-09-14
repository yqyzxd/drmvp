package com.wind.drmvp.hunt.mvp.view.impl;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.layout.MvpLinearLayout;
import com.wind.base.di.HasComponent;
import com.wind.data.base.bean.UserInfo;
import com.wind.data.hunt.request.PermissionRequest;
import com.wind.drmvp.R;
import com.wind.drmvp.base.App;
import com.wind.drmvp.hunt.activity.ChatActivity;
import com.wind.drmvp.hunt.bean.Permission;
import com.wind.drmvp.hunt.bean.PermissionDetail;
import com.wind.drmvp.hunt.di.ChatComponent;
import com.wind.drmvp.hunt.di.ChatModule;
import com.wind.drmvp.hunt.mvp.presenter.ChatPresenter;
import com.wind.drmvp.hunt.mvp.view.ChatView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wind on 16/9/12.
 */

public class ChatMvpLayout extends MvpLinearLayout<ChatView,ChatPresenter> implements
        ChatView,
        HasComponent<ChatComponent>{

    ChatComponent mComponent;
    public ChatMvpLayout(Context context) {
        super(context);
        init();
    }

    public ChatMvpLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChatMvpLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ChatMvpLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.view_chat,this);
        ButterKnife.bind(this,this);
        initializeInjector();
        injectDependencies();
    }

    @OnClick(R.id.tv_chat)
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.tv_chat:
                presenter.execute(buildPermissionRequest(Permission.CHAT));
                break;
        }
    }

    private void initializeInjector(){
        mComponent= App.get()
                .appComponent()
                .plus(new ChatModule(this));
    }
    private void injectDependencies() {
        inject();
    }

    protected  void inject(){
        getComponent().inject(this);
    }

    @Override
    public ChatPresenter createPresenter() {
        return mComponent.presenter();
    }

    @Override
    public ChatComponent getComponent() {
        return mComponent;
    }

    @Override
    public void checkPermisionSuccess() {
        //presenter.execute(buildRequest());
       // ToastUtil.showToast(getContext(),"有权限");
        Intent intent=new Intent(getContext(), ChatActivity.class);
        intent.putExtra(ChatActivity.EXTRA_KEY_NAME,mUserInfo.getBaseUserInfo().getName());
        getContext().startActivity(intent);
    }



    @Override
    public void noPermision(int errCode) {

    }

    @Override
    public void parsePermissionError(int errCode) {

    }

    @Override
    public void parsePermissionErrorSuccess(PermissionDetail detail) {

    }

    private UserInfo mUserInfo;
    public void setUser(UserInfo user){
        this.mUserInfo=user;
    }
    public PermissionRequest buildPermissionRequest(String operationType) {

        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setPowerType(operationType);
        permissionRequest.setToken(App.get().getLoginUser().getBaseUserInfo().getToken());
        if (mUserInfo != null)
            permissionRequest.setToUid(mUserInfo.getBaseUserInfo().getUid());
        return permissionRequest;
    }
}
