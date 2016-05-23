package com.wind.data.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wind on 16/5/19.
 */
public class BaseResponse {
    /**
     * 成功
     */
    public static final int CODE_SUCCESS=0;
    /**
     * 错误码
     */

    @SerializedName("err")
    public int errCode;

    /**
     * 错误信息
     */
    @SerializedName("msg")
    public String errMsg;

    /**
     * 时间戳
     */
    public String timestamp;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
