package com.wind.drmvp.user.presenter;

import com.wind.base.request.BaseRequest;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.base.usecase.Usecase;
import com.wind.base.usecase.UsecaseCompoment;
import com.wind.base.usecase.UsecaseManager;
import com.wind.domain.user.interactor.UserUsecase;
import com.wind.drmvp.base.ExecutePresenter;
import com.wind.drmvp.user.subscriber.UserSubscribe;
import com.wind.drmvp.user.view.UserView;

/**
 * Created by wind on 16/5/23.
 */
public class UserPresenter<V extends UserView>  extends ExecutePresenter<V> {

    private Usecase<LoginRequest,LoginResponse> usecase;
    private UsecaseManager manager;
    public UserPresenter(UserUsecase usecase){
        this.usecase=usecase;

    }

    @Override
    public void attachView(V mvpView) {
        super.attachView(mvpView);
        manager=new UsecaseManager();
        manager.addUsercaseCompoment(new UsecaseCompoment(new UserSubscribe(this.getView()),usecase));

    }

    @Override
    public void execute(BaseRequest request) {
       // usecase.execute(request,new UserSubscribe(this.getView()));
        manager.execute(request);
    }



}
