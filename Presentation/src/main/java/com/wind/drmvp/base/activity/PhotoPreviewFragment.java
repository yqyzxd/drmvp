package com.wind.drmvp.base.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wind.drmvp.R;
import com.wind.drmvp.base.bean.PhotoPreview;
import com.wind.drmvp.widget.TouchImageView;

import java.util.ArrayList;
import java.util.List;


public class PhotoPreviewFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private static final String ARGS_KEY_PHOTOS = "args_key_photos";
    private static final String ARGS_KEY_POSITION = "args_key_position";
    ViewPager viewpager;
    private List<PhotoPreview> photos;


    TextView tv_fraction;
    View toolbar_iv_back;
    private int mCurrentPosition;
    private PhotoPagerAdapter photoPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_preview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        photoPagerAdapter = new PhotoPagerAdapter();
        viewpager.setAdapter(photoPagerAdapter);
        viewpager.setOnPageChangeListener(this);

        ArrayList<PhotoPreview> photos= (ArrayList<PhotoPreview>) getArguments().getSerializable(ARGS_KEY_PHOTOS);
        int position=getArguments().getInt(ARGS_KEY_POSITION);
        setPhotos(photos,position);
    }

    private void initView(View view) {
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);



        tv_fraction = (TextView) view.findViewById(R.id.tv_fraction);


        toolbar_iv_back = view.findViewById(R.id.toolbar_iv_back);
        toolbar_iv_back.setOnClickListener(this);

    }


    public static PhotoPreviewFragment getInstance(ArrayList<PhotoPreview> photos, int position){
        PhotoPreviewFragment fragment=new PhotoPreviewFragment();
        Bundle args=new Bundle();
        args.putSerializable(ARGS_KEY_PHOTOS,photos);
        args.putInt(ARGS_KEY_POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }
    public void setPhotos(ArrayList<PhotoPreview> photos, int position) {
        this.photos = photos;
        mCurrentPosition = position;
        photoPagerAdapter.addAll(photos, mCurrentPosition);
        tv_fraction.setText((position + 1) + "/" + photos.size());
        // setSelectedPhotosCount();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        tv_fraction.setText((position + 1) + "/" + photos.size());
       /* if (photos.get(mCurrentPosition).isSelected()) {
            iv_select.setActivated(true);
        } else {
            iv_select.setActivated(false);
        }*/
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    @Override
    public void onClick(View view) {


    }

    private class PhotoPagerAdapter extends PagerAdapter {
        private List<PhotoPreview> photos;

        PhotoPagerAdapter() {
        }

        @Override
        public int getCount() {
            return this.photos == null ? 0 : this.photos.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public View instantiateItem(final ViewGroup container, final int position) {
            View view=getActivity().getLayoutInflater().inflate(R.layout.item_photopreview,null,false);
            final TouchImageView iv = (TouchImageView)view.findViewById(R.id.iv);

            iv.setMaxZoom(5f);
            iv.setMinZoom(1f);
           // ViewCompat.setTransitionName(view.findViewById(R.id.background),photos.get(position).getPath()+"_background");
            ViewCompat.setTransitionName(iv,photos.get(position).getPath());
            Glide.with(getActivity())
                    .load(this.photos.get(position).getPath())
                    .asBitmap()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            iv.setImageBitmap(resource);
                            if (mCurrentPosition==position){
                                startPostponedEnterTransition(iv);
                            }
                        }
                    });
            container.addView(iv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return iv;
        }

        /**
         * 若不返回POSITION_NONE,则即使调用notifyDataSetChanged();
         * 但是ViewPager还是不会更新原来的数据。
         *
         * @param object
         * @return
         */
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            view.clearAnimation();
            ((ViewPager) container).removeView(view);
            view = null;
        }

        public void addAll(List<PhotoPreview> photos, final int curPosition) {
            this.photos = photos;
            notifyDataSetChanged();
            viewpager.setCurrentItem(curPosition);


        }
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