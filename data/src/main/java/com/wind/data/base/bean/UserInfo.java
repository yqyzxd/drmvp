package com.wind.data.base.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wind on 16/5/19.
 */
public class UserInfo {
    private int id;
    @SerializedName("basic")
    private BaseUserInfo baseUserInfo;


    private Status status;

    private SpouseCriteria mate;

    @SerializedName("more_info")
    private MoreInfo moreInfo;

    public BaseUserInfo getBaseUserInfo() {
        return baseUserInfo;
    }

    public void setBaseUserInfo(BaseUserInfo baseUserInfo) {
        this.baseUserInfo = baseUserInfo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SpouseCriteria getMate() {
        return mate;
    }

    public void setMate(SpouseCriteria mate) {
        this.mate = mate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(MoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }
}
