package com.wind.drmvp.login.mvp.presenter;

import com.wind.base.request.BaseRequest;
import com.wind.data.login.request.LoginRequest;
import com.wind.data.login.response.LoginResponse;
import com.wind.base.usecase.Usecase;
import com.wind.base.usecase.UsecaseCompoment;
import com.wind.base.usecase.UsecaseManager;
import com.wind.drmvp.base.ExecutePresenter;
import com.wind.drmvp.login.mvp.view.LoginView;
import com.wind.drmvp.login.subscriber.LoginSubscriber;

import javax.inject.Inject;

/**
 * Created by wind on 16/5/18.
 */
public class LoginPresenter extends ExecutePresenter<LoginView> {

    Usecase<LoginRequest,LoginResponse> usecase;
    UsecaseManager manager;
    @Inject
    public LoginPresenter(Usecase<LoginRequest,LoginResponse> usecase){
        this.usecase=usecase;

    }

    @Override
    public void attachView(LoginView mvpView) {
        super.attachView(mvpView);
        manager=new UsecaseManager();
        manager.addUsercaseCompoment(new UsecaseCompoment(new LoginSubscriber(getView()),usecase));
    }


    @Override
    public void execute(BaseRequest request){
        this.manager.execute(request);
    }


}
