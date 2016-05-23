package com.wind.data.base.bean;

import java.io.Serializable;

/**
 * Created by shi on 2015/9/19.
 */
public class Img implements Serializable{

    private int id;
   // private int id;
    private String url;
    private String param;
    private int type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
