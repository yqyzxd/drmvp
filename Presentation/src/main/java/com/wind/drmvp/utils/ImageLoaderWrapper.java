package com.wind.drmvp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by wind on 16/5/23.
 */
public class ImageLoaderWrapper {

    public static void displayImage(Context context, String uri, ImageView imageView) {
        Glide
                .with(context)
                .load(uri)
                .into(imageView);

    }
}
