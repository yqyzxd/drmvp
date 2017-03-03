package com.wind.base.request;

/**
 * Created by wind on 16/5/19.
 */
public class BaseRequest {
    public static final int LOAD_FROM_WEB=0;
    public static final int LOAD_FROM_LOCAL=1;
    protected int loadFrom;

    protected String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLoadFrom() {
        return loadFrom;
    }

    public void setLoadFrom(int loadFrom) {
        this.loadFrom = loadFrom;
    }
}
