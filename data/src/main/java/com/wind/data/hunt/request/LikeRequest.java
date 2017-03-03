package com.wind.data.hunt.request;

import com.wind.base.request.BaseRequest;

/**
 * Created by wind on 16/9/12.
 */

public class LikeRequest extends BaseRequest {

    private String uid;

    private boolean isLike;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
