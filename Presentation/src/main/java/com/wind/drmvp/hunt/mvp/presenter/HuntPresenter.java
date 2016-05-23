package com.wind.drmvp.hunt.mvp.presenter;

import com.wind.data.base.BaseResponse;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.domain.Usecase;
import com.wind.domain.user.interactor.UserUsecase;
import com.wind.drmvp.base.App;
import com.wind.drmvp.hunt.mvp.view.HuntView;
import com.wind.drmvp.user.presenter.UserPresenter;

import javax.inject.Inject;

import rx.Subscriber;


/**
 * Created by wind on 16/5/20.
 */
public class HuntPresenter extends UserPresenter<HuntView> {

    private Usecase<HuntRequest,HuntResponse> usecase;

    @Inject
    public HuntPresenter(Usecase<HuntRequest,HuntResponse> usecase, UserUsecase userUsecase){
        super(userUsecase);
        this.usecase=usecase;
    }

    public void getUserList(HuntRequest request) {
        this.usecase.execute(request,new HuntSubscriber());
    }



    private final class HuntSubscriber extends Subscriber<HuntResponse> {

        @Override public void onCompleted() {
        }

        @Override public void onError(Throwable e) {
            e.printStackTrace();
            showNetworkError(App.get());
        }

        @Override public void onNext(HuntResponse response) {
            if (isViewAttached()){
                if (response.getErrCode()== BaseResponse.CODE_SUCCESS){
                    getView().showUsers(response);
                }else {
                    getView().showError(response.getErrMsg());
                }
            }
        }
    }
}
