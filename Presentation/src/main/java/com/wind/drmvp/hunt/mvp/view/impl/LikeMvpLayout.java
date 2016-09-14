package com.wind.drmvp.hunt.mvp.view.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.hannesdorfmann.mosby.mvp.layout.MvpLinearLayout;
import com.wind.base.di.HasComponent;
import com.wind.base.utils.ToastUtil;
import com.wind.data.base.bean.UserInfo;
import com.wind.data.hunt.request.LikeRequest;
import com.wind.data.hunt.request.PermissionRequest;
import com.wind.drmvp.R;
import com.wind.drmvp.base.App;
import com.wind.drmvp.hunt.bean.Permission;
import com.wind.drmvp.hunt.bean.PermissionDetail;
import com.wind.drmvp.hunt.di.LikeComponent;
import com.wind.drmvp.hunt.di.LikeModule;
import com.wind.drmvp.hunt.mvp.presenter.LikePresenter;
import com.wind.drmvp.hunt.mvp.view.LikeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wind on 16/9/12.
 */

public class LikeMvpLayout  extends MvpLinearLayout<LikeView,LikePresenter> implements
        LikeView,
        HasComponent<LikeComponent> {
    public LikeMvpLayout(Context context) {
        super(context);
        init();
    }

    public LikeMvpLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikeMvpLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LikeMvpLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    LikeComponent mComponent;


    @Bind(R.id.iv_like)
    ImageView iv_like;

    private void init() {
        inflate(getContext(), R.layout.view_like,this);
        ButterKnife.bind(this,this);
        initializeInjector();
        injectDependencies();
    }

    private void initializeInjector(){
        mComponent= App.get()
                .appComponent()
                .plus(new LikeModule(this));
    }
    private void injectDependencies() {
        inject();
    }

    protected  void inject(){
        getComponent().inject(this);
    }

    @Override
    public LikePresenter createPresenter() {
        return mComponent.presenter();
    }

    @Override
    public LikeComponent getComponent() {
        return mComponent;
    }
    @OnClick(R.id.iv_like)
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.iv_like:
                presenter.execute(buildPermissionRequest(Permission.LIKE));
                break;
        }
    }

    private UserInfo mUserInfo;
    public void setUser(UserInfo user){
        this.mUserInfo=user;


        setImage();
    }

    public void setImage(){

        if (mUserInfo.getStatus().getLiked()==0){
            iv_like.setImageResource(R.drawable.my_unlike);
        }else{
            iv_like.setImageResource(R.drawable.my_like);
        }
    }



    @Override
    public void checkPermisionSuccess() {
        presenter.execute(buildRequest());
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

    public PermissionRequest buildPermissionRequest(String operationType) {

        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setPowerType(operationType);
        permissionRequest.setToken(App.get().getLoginUser().getBaseUserInfo().getToken());
        if (mUserInfo != null)
            permissionRequest.setToUid(mUserInfo.getBaseUserInfo().getUid());
        return permissionRequest;
    }

    private LikeRequest buildRequest() {
        LikeRequest request=new LikeRequest();
        request.setToken(App.get().getLoginUser().getBaseUserInfo().getToken());
        request.setUid(mUserInfo.getBaseUserInfo().getUid());
        boolean isLike=mUserInfo.getStatus().getLiked()==0?true:false;
        request.setLike(isLike);
        return request;
    }


    @Override
    public void likeUnLikeSuccess() {
        ToastUtil.showToast(getContext(),"like unlike success");
        int liked=mUserInfo.getStatus().getLiked()==0?1:0;
        mUserInfo.getStatus().setLiked(liked);
        setImage();
    }

    @Override
    public void likeUnLikeFailed(String msg) {
        ToastUtil.showToast(getContext(),"like failed");
    }


}
