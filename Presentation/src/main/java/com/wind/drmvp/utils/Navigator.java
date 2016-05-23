package com.wind.drmvp.utils;

import android.support.v4.app.FragmentActivity;

import com.wind.base.utils.NavigateManager;
import com.wind.drmvp.hunt.activity.HuntActivity;

/**
 * Created by wind on 16/5/23.
 */
public class Navigator {


    public static void startHuntActivity(FragmentActivity activity) {
        NavigateManager.overlay(activity, HuntActivity.class);
    }
}
