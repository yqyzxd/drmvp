package com.wind.base.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.wind.base.bean.UploadFile;

/**
 * Created by wind on 2017/3/1.
 */

public class UploadFileResponse extends BaseResponse{

    @JSONField(name = "item")
    private String uploadedUrl;

    private UploadFile uploadFile;

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadedUrl() {
        return uploadedUrl;
    }

    public void setUploadedUrl(String uploadedUrl) {
        this.uploadedUrl = uploadedUrl;
    }
}
