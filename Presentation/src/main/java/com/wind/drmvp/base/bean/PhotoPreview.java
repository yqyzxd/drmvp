package com.wind.drmvp.base.bean;

import java.io.Serializable;

/**
 * Created by wind on 2017/3/2.
 */

public class PhotoPreview implements Serializable{

    private String path;

    private int resId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
