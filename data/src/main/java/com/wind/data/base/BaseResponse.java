package com.wind.data.base;

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

    private int err;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 时间戳
     */
    public String timestamp;

    public int getErrCode() {
        return err;
    }


    public void setErr(int err) {
        this.err = err;
    }

    public String getErrMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
