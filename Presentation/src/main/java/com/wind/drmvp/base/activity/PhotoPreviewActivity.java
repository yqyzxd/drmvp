package com.wind.drmvp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wind.drmvp.R;
import com.wind.drmvp.base.bean.PhotoPreview;

import java.util.ArrayList;

/**
 * Created by wind on 2017/3/2.
 */

public class PhotoPreviewActivity extends FragmentActivity {

    public static final String EXTRA_KEY_PHOTOS = "extra_key_photos";
    public static final String EXTRA_KEY_POSITION = "extra_key_position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        postponeEnterTransition();

        ArrayList<PhotoPreview> photos= (ArrayList<PhotoPreview>) getIntent().getSerializableExtra(EXTRA_KEY_PHOTOS);
        int position=getIntent().getIntExtra(EXTRA_KEY_POSITION,0);
        Fragment fragment=PhotoPreviewFragment.getInstance(photos,position);
        replaceFragment(fragment);


    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.fl_fragment_container,fragment);
        transaction.commitAllowingStateLoss();

    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
