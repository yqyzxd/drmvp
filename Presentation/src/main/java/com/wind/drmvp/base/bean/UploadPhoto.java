package com.wind.drmvp.base.bean;

import com.wind.base.bean.UploadFile;

/**
 * Created by wind on 2017/3/1.
 */
public class UploadPhoto extends UploadFile{
    public UploadPhoto(String path){
        super(path);
    }
    public UploadPhoto(int resId){
        this.resId=resId;
    }

    /**
     * app中的资源图片
     */
    private int resId;


    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
