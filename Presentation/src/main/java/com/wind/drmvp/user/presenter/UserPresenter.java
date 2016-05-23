package com.wind.drmvp.user.presenter;

import com.wind.base.mvp.presenter.MvpBasePresenter;
import com.wind.data.base.BaseResponse;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.domain.Usecase;
import com.wind.domain.user.interactor.UserUsecase;
import com.wind.drmvp.user.view.UserView;

import rx.Subscriber;

/**
 * Created by wind on 16/5/23.
 */
public class UserPresenter<V extends UserView>  extends MvpBasePresenter<V>{

    private Usecase<LoginRequest,LoginResponse> usecase;

    public UserPresenter(UserUsecase usecase){
        this.usecase=usecase;
    }

    public void getLoginUser() {
        usecase.execute(null,new UserSubscribe());
    }


    private final class UserSubscribe extends Subscriber<LoginResponse>{
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(LoginResponse response) {
            if (isViewAttached()){
                if (response.getErrCode()== BaseResponse.CODE_SUCCESS){
                    getView().getLoginUserReturn(response);
                }
            }
        }
    }
}
