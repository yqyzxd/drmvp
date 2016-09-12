package com.wind.drmvp.hunt.mvp.presenter;

import com.wind.data.hunt.request.LikeRequest;
import com.wind.data.hunt.request.PermissionRequest;
import com.wind.data.hunt.response.LikeResponse;
import com.wind.data.hunt.response.PermissionResponse;
import com.wind.domain.Usecase;
import com.wind.domain.UsecaseCompoment;
import com.wind.drmvp.base.ExecutePresenter;
import com.wind.drmvp.hunt.mvp.view.LikeView;
import com.wind.drmvp.hunt.subscriber.LikeSubscriber;
import com.wind.drmvp.hunt.subscriber.PermissionSubscriber;

import javax.inject.Inject;

/**
 * Created by wind on 16/9/12.
 */
public class LikePresenter extends ExecutePresenter<LikeView> {


    private Usecase<LikeRequest,LikeResponse> likeUsecase;
    private Usecase<PermissionRequest,PermissionResponse> permissionUsecase;
    @Inject
    public LikePresenter(Usecase<LikeRequest,LikeResponse> likeUsecase,
                         Usecase<PermissionRequest,PermissionResponse> permissionUsecase){
        this.likeUsecase=likeUsecase;
        this.permissionUsecase=permissionUsecase;
    }


    @Override
    public void attachView(LikeView view) {
        super.attachView(view);
        manager
                .addUsercaseCompoment(new UsecaseCompoment(new PermissionSubscriber(getView()),permissionUsecase))
                .addUsercaseCompoment(new UsecaseCompoment(new LikeSubscriber(getView()),likeUsecase));
    }


}
