package com.wind.data.login.request;

import com.wind.data.base.BaseRequest;

/**
 * Created by wind on 16/5/19.
 */
public class LoginRequest extends BaseRequest{
    public static final String FIELD_USERNAME="phone";
    public static final String FIELD_PASSWORD="password";
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
