package com.wind.drmvp.hunt.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.wind.data.hunt.request.PermissionRequest;
import com.wind.drmvp.hunt.bean.PermissionDetail;

/**
 * Created by wind on 16/9/12.
 */

public interface PermissionView extends MvpView {
    /**
     * 检查是否有权限
     */
    //void checkPermision(String operationType);

    /**
     * 有权限
     */
    void checkPermisionSuccess();

    /**
     * 无权限
     */
    void noPermision(int errCode);

    void parsePermissionError(int errCode);

    void parsePermissionErrorSuccess(PermissionDetail detail);


    PermissionRequest buildPermissionRequest(String operationType);


}
