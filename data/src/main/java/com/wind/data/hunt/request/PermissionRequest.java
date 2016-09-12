package com.wind.data.hunt.request;

import com.wind.data.base.BaseRequest;

/**
 * Created by wind on 16/9/12.
 */

public class PermissionRequest extends BaseRequest {

    //查询哪个权限
    private String powerType;

    //聊天时为必填
    private String toUid;

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }
}
