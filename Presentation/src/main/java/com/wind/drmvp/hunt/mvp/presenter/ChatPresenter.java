package com.wind.drmvp.hunt.mvp.presenter;

import com.wind.data.hunt.request.PermissionRequest;
import com.wind.data.hunt.response.PermissionResponse;
import com.wind.domain.Usecase;
import com.wind.domain.UsecaseCompoment;
import com.wind.drmvp.base.ExecutePresenter;
import com.wind.drmvp.hunt.mvp.view.ChatView;
import com.wind.drmvp.hunt.subscriber.PermissionSubscriber;

import javax.inject.Inject;

/**
 * Created by wind on 16/9/12.
 */

public class ChatPresenter extends ExecutePresenter<ChatView> {

    private Usecase<PermissionRequest,PermissionResponse> permissionUsecase;
    @Inject
    public ChatPresenter(Usecase<PermissionRequest,PermissionResponse> permissionUsecase){
        this.permissionUsecase=permissionUsecase;
    }

    @Override
    public void attachView(ChatView view) {
        super.attachView(view);
        manager
                .addUsercaseCompoment(new UsecaseCompoment(new PermissionSubscriber(getView()),permissionUsecase));
    }
}
