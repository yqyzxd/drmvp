package com.wind.data.hunt.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.wind.data.base.bean.UserInfo;

import java.util.List;

/**
 * Created by wind on 16/5/20.
 */
public class Hunt {
    @SerializedName("max_time")
    @JSONField(name = "max_time")
    private String timestamp;
    @JSONField(name = "order_rand")
    @SerializedName("order_rand")
    private String orderRand;
    @SerializedName("user")
    @JSONField(name = "user")
    private List<UserInfo> userInfos;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public String getOrderRand() {
        return orderRand;
    }

    public void setOrderRand(String orderRand) {
        this.orderRand = orderRand;
    }
}
