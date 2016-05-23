package com.wind.data.login.response;

import com.google.gson.annotations.SerializedName;
import com.wind.data.base.BaseResponse;
import com.wind.data.base.bean.UserInfo;


/**
 * Created by wind on 16/5/19.
 */
public class LoginResponse extends BaseResponse {
    @SerializedName("items")
    private UserInfo loginUser;

    public UserInfo getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserInfo loginUser) {
        this.loginUser = loginUser;
    }

}
