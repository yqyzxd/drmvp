package com.wind.drmvp.base.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wind.drmvp.R;
import com.wind.drmvp.base.bean.PhotoPreview;

import java.util.List;

import uk.co.senab.photoview2.PhotoView;
import uk.co.senab.photoview2.PhotoViewAttacher;


public class PhotoPreviewFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PHOTO_POSITION = "args_photo_position";
    private static final String ARG_STARTING_PHOTO_POSITION = "args_starting_photo_position";
    private static final String ARGS_KEY_PHOTO = "args_photo";

    private List<PhotoPreview> photos;


    TextView tv_fraction;
    View toolbar_iv_back;
    PhotoView mPhotoView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_preview, container, false);
    }

    private int mStartingPosition,mPhotoPosition;
    private boolean mIsTransitioning;
    private PhotoPreview mPhoto;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStartingPosition = getArguments().getInt(ARG_STARTING_PHOTO_POSITION);
        mPhotoPosition = getArguments().getInt(ARG_PHOTO_POSITION);
        mIsTransitioning = savedInstanceState == null && mStartingPosition == mPhotoPosition;
        mPhoto= (PhotoPreview) getArguments().getSerializable(ARGS_KEY_PHOTO);

    }
    public ImageView getPreviewView() {
        return mPhotoView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);



        final PhotoViewAttacher attacher = new PhotoViewAttacher(mPhotoView);
        ViewCompat.setTransitionName(mPhotoView,mPhoto.getPath());
        Glide.with(getActivity())
                .load(this.mPhoto.getPath())
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.ic_launcher)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        mPhotoView.setImageBitmap(resource);
                        attacher.update();
                        if (mStartingPosition==mPhotoPosition){
                            startPostponedEnterTransition(mPhotoView);
                        }
                    }
                });
    }

    private void initView(View view) {
        mPhotoView= (PhotoView) view.findViewById(R.id.photo_view);
        tv_fraction = (TextView) view.findViewById(R.id.tv_fraction);
        toolbar_iv_back = view.findViewById(R.id.toolbar_iv_back);
        toolbar_iv_back.setOnClickListener(this);
    }








    @Override
    public void onClick(View view) {


    }



    public static Fragment newInstance(int position, int startingPosition, PhotoPreview photo) {
        Bundle args = new Bundle();
        args.putInt(ARG_PHOTO_POSITION, position);
        args.putInt(ARG_STARTING_PHOTO_POSITION, startingPosition);
        args.putSerializable(ARGS_KEY_PHOTO,photo);
        PhotoPreviewFragment fragment = new PhotoPreviewFragment();
        fragment.setArguments(args);
        return fragment;
    }



    private void startPostponedEnterTransition(final View view) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                ActivityCompat.startPostponedEnterTransition(getActivity());
                return true;
            }
        });
    }


}