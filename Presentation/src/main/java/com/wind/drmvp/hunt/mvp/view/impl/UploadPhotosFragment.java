package com.wind.drmvp.hunt.mvp.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.wind.base.bean.UploadFile;
import com.wind.base.mvp.view.DaggerMvpFragment;
import com.wind.base.request.UploadFileRequest;
import com.wind.drmvp.R;
import com.wind.drmvp.base.activity.PhotoPreviewActivity;
import com.wind.drmvp.base.bean.PhotoPreview;
import com.wind.drmvp.base.bean.UploadPhoto;
import com.wind.drmvp.hunt.adapter.PhotoAdapter;
import com.wind.drmvp.hunt.di.UploadPhotosComponent;
import com.wind.drmvp.hunt.mvp.presenter.UploadPhotosPresenter;
import com.wind.drmvp.hunt.mvp.view.UploadPhotosView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wind on 2017/3/1.
 */

public class UploadPhotosFragment extends DaggerMvpFragment<UploadPhotosView, UploadPhotosPresenter, UploadPhotosComponent>
        implements UploadPhotosView {

    private int totalTextCount = 140;
    @Bind(R.id.et_content)
    EditText et_content;

    @Bind(R.id.tv_count)
    TextView tv_count;

    @Bind(R.id.gv_photos)
    GridView mGvPhotos;

    PhotoAdapter mAdapter;

    @Bind(R.id.tv_commit)
    TextView tv_commit;

    @NonNull
    @Override
    public UploadPhotosPresenter createPresenter() {
        return getComponent().presenter();
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.mine_fragment_upload_photos;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mAdapter = new PhotoAdapter(getActivity(), R.layout.item_photo);
        mGvPhotos.setAdapter(mAdapter);
        mGvPhotos.setVisibility(View.VISIBLE);
        UploadPhoto photo = new UploadPhoto(R.drawable.mu_add_photo);

        String path = "/storage/emulated/0/MarryU/ImgTmp/1486454961092.jpg";
        UploadPhoto photo2 = new UploadPhoto(path);

        mAdapter.add(photo);
        //mAdapter.add(photo2);

        mGvPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position == 0) {
                    RxGalleryFinal
                            .with(getActivity())
                            .image()
                            .multiple()
                            .maxSize(8)
                            .imageLoader(ImageLoaderType.GLIDE)
                            .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
                                @Override
                                protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                    List<UploadPhoto> photoList = new ArrayList<>();
                                    for (MediaBean bean : imageMultipleResultEvent.getResult()) {
                                        String path = bean.getOriginalPath();
                                        UploadPhoto photo = new UploadPhoto(path);
                                        photoList.add(photo);
                                    }
                                    mAdapter.addAll(photoList);
                                }
                            })
                            .openGallery();
                } else {
                    previewPhotos(position-1,view);
                }
            }
        });
    }


    private void previewPhotos(final int position,final View shareElement) {
        List<UploadPhoto> photos = mAdapter.getData();


        /*photos.remove(0);
        ArrayList<PhotoPreview> previews=new ArrayList<>();
        for (UploadPhoto item:photos){
            PhotoPreview preview=new PhotoPreview();
            preview.setPath(item.getPath());
            previews.add(preview);
        }*/

        Observable.from(photos).filter(new Func1<UploadPhoto, Boolean>() {
            @Override
            public Boolean call(UploadPhoto uploadPhoto) {
                return !TextUtils.isEmpty(uploadPhoto.getPath());
            }
        })
                .map(new Func1<UploadPhoto, PhotoPreview>() {
                    @Override
                    public PhotoPreview call(UploadPhoto uploadPhoto) {
                        PhotoPreview preview = new PhotoPreview();
                        preview.setPath(uploadPhoto.getPath());
                        return preview;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotoPreview>() {
                    private ArrayList<PhotoPreview> previews = new ArrayList<>();

                    @Override
                    public void onCompleted() {
                        Intent intent = new Intent(getActivity(), PhotoPreviewActivity.class);
                        intent.putExtra(PhotoPreviewActivity.EXTRA_KEY_PHOTOS, previews);
                        intent.putExtra(PhotoPreviewActivity.EXTRA_KEY_POSITION, position);
                        /*ActivityOptionsCompat options=AppCompat.makeSceneTransitionAnimation(getActivity(),shareElement);
                        AppCompat.makeSceneTransitionAnimation()*/

                        Pair pair1=Pair.create(shareElement, ViewCompat.getTransitionName(shareElement));
                        ActivityOptionsCompat options=ActivityOptionsCompat
                                .makeSceneTransitionAnimation(getActivity(),pair1);

                        ActivityCompat.startActivity(getActivity(),intent,options.toBundle());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PhotoPreview photoPreview) {
                        previews.add(photoPreview);
                    }
                });
    }

    @OnClick({R.id.tv_commit})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_commit:
                presenter.execute(buildUploadRequest());
                break;
        }
    }

    private UploadFileRequest buildUploadRequest() {

        UploadFileRequest request = new UploadFileRequest();

        List<UploadPhoto> statefulPhotoList = mAdapter.getData();


        List<UploadFile> list = new ArrayList<>();
        for (UploadPhoto item : statefulPhotoList) {
            list.add(item);
        }
        request.setUploadFileList(list);
        return request;
    }


    @Override
    public void onUploadError() {

    }

    @Override
    public void noPicFound(String path) {

    }

    @Override
    public void updateProgress(long current, long total) {

    }


    @Override
    public void commitData() {


    }


    @Override
    public void commitDataSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void uploadPhotos() {

    }

    @Override
    public void onUploadSuccess() {
        List<UploadPhoto> uploadPhotos = mAdapter.getData();
        for (UploadPhoto item : uploadPhotos) {
            if (!TextUtils.isEmpty(item.getUploadedUrl()))
                Log.e("URL", item.getUploadedUrl());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static Fragment getInstance() {
        UploadPhotosFragment fragment = new UploadPhotosFragment();
        return fragment;
    }


}
