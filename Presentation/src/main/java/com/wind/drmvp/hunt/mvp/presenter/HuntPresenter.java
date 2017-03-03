package com.wind.drmvp.hunt.mvp.presenter;

import com.wind.base.request.BaseRequest;
import com.wind.base.usecase.Usecase;
import com.wind.base.usecase.UsecaseCompoment;
import com.wind.base.usecase.UsecaseManager;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.domain.hunt.interactor.HuntPageUsecase;
import com.wind.domain.user.interactor.UserUsecase;
import com.wind.drmvp.base.ExecutePresenter;
import com.wind.drmvp.hunt.mvp.view.HuntView;
import com.wind.drmvp.hunt.subscriber.HuntSubscriber;
import com.wind.drmvp.user.subscriber.UserSubscribe;

import javax.inject.Inject;


/**
 * Created by wind on 16/5/20.
 */
public class HuntPresenter extends ExecutePresenter<HuntView> {

    private Usecase<HuntRequest,HuntResponse> usecase;
    private UsecaseManager usecaseManager;
    private UserUsecase userUsecase;
    private HuntPageUsecase pageUsecase;
   /* @Inject
    public HuntPresenter(Usecase<HuntRequest,HuntResponse> usecase, UserUsecase userUsecase){
        this.usecase=usecase;
        this.userUsecase=userUsecase;


    }*/
    @Inject
    public HuntPresenter(HuntPageUsecase pageUsecase, UserUsecase userUsecase){
        this.pageUsecase=pageUsecase;
        this.userUsecase=userUsecase;


    }

    @Override
    public void attachView(HuntView mvpView) {
        super.attachView(mvpView);
        usecaseManager=new UsecaseManager();
        usecaseManager
                .addUsercaseCompoment(new UsecaseCompoment(new HuntSubscriber(getView()),pageUsecase))
                .addUsercaseCompoment(new UsecaseCompoment(new UserSubscribe(getView()),userUsecase));
    }

    @Override
    public void execute(BaseRequest request){
        this.usecaseManager.execute(request);
    }



}
