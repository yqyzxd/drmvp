package com.wind.drmvp.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wind.drmvp.R;
import com.wind.drmvp.base.bean.PhotoPreview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wind on 2017/3/2.
 */

public class PhotoPreviewActivity extends FragmentActivity {

    public static final String EXTRA_KEY_PHOTOS = "extra_key_photos";
    public static final String EXTRA_KEY_START_POSITION = "extra_key_position";
    public static final String EXTRA_KEY_CURRENT_POSITION = "extra_key_current_position";
    private boolean mIsReturning;

    private int mCurrentPosition;
    private int mStartingPosition;
    private static final String STATE_CURRENT_PAGE_POSITION = "state_current_page_position";
    private final SharedElementCallback mCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (mIsReturning) {
                ImageView sharedElement = mCurrentFragment.getPreviewView();
                if (sharedElement == null) {
                    // If shared element is null, then it has been scrolled off screen and
                    // no longer visible. In this case we cancel the shared element transition by
                    // removing the shared element from the shared elements map.
                    names.clear();
                    sharedElements.clear();
                } else if (mStartingPosition != mCurrentPosition) {
                    // If the user has swiped to a different ViewPager page, then we need to
                    // remove the old shared element and replace it with the new shared element
                    // that should be transitioned instead.
                    names.clear();
                    names.add(ViewCompat.getTransitionName(sharedElement));
                    sharedElements.clear();
                    sharedElements.put(ViewCompat.getTransitionName(sharedElement), sharedElement);
                }
            }
        }
    };

    private PhotoPreviewFragment mCurrentFragment;
    private ArrayList<PhotoPreview> mPhotos;
    private ViewPager mPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);
        ActivityCompat.postponeEnterTransition(this);
        setEnterSharedElementCallback(mCallback);
        mPhotos= (ArrayList<PhotoPreview>) getIntent().getSerializableExtra(EXTRA_KEY_PHOTOS);
        mStartingPosition=getIntent().getIntExtra(EXTRA_KEY_START_POSITION,0);
        if (savedInstanceState == null) {
            mCurrentPosition = mStartingPosition;
        } else {
            mCurrentPosition = savedInstanceState.getInt(STATE_CURRENT_PAGE_POSITION);
        }

        mPager= (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new PhotoFragmentPagerAdapter(getSupportFragmentManager()));
        mPager.setCurrentItem(mCurrentPosition);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }
        });
        //mFragment=PhotoPreviewFragment.getInstance(photos,position);
        //replaceFragment(mFragment);


    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CURRENT_PAGE_POSITION, mCurrentPosition);
    }


    @Override
    public void onBackPressed() {
        mIsReturning = true;
        Intent data = new Intent();

        data.putExtra(EXTRA_KEY_START_POSITION, mStartingPosition);
        data.putExtra(EXTRA_KEY_CURRENT_POSITION, mCurrentPosition);
        setResult(RESULT_OK, data);
        ActivityCompat.finishAfterTransition(this);
    }

    private class PhotoFragmentPagerAdapter extends FragmentStatePagerAdapter {
        public PhotoFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PhotoPreviewFragment.newInstance(position, mStartingPosition,mPhotos.get(position));
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            mCurrentFragment = (PhotoPreviewFragment) object;
        }

        @Override
        public int getCount() {
            return mPhotos.size();
        }
    }
}
