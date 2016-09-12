package com.wind.drmvp.hunt.mvp.view;

/**
 * Created by wind on 16/9/12.
 */

public interface LikeView extends PermissionView{

    void likeUnLikeSuccess();
    void likeUnLikeFailed(String msg);
}
