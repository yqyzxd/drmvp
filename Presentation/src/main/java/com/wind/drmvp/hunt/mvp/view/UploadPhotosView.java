package com.wind.drmvp.hunt.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by sh on 2015/10/9.
 */
public interface UploadPhotosView extends MvpView {

    /**
     * 提交数据
     * 个人图片
     */
    void commitData();

    /**
     * 提交数据成功
     */
    void commitDataSuccess();
    void showLoading();
    //void showError();

    void uploadPhotos();

    /**
     * photoModel指向的图片上传成功
     */
    void onUploadSuccess();

    /**
     *  photoModel指向的图片上传失败

     */
    void onUploadError();

    //照片不存在
    void noPicFound(String path);

    void updateProgress(long current, long total);
}
