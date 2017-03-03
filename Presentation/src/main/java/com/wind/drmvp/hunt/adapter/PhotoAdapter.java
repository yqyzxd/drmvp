package com.wind.drmvp.hunt.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wind.base.adapter.BaseAdapterHelper;
import com.wind.base.adapter.QuickAdapter;
import com.wind.base.utils.AppCompat;
import com.wind.drmvp.R;
import com.wind.drmvp.base.bean.UploadPhoto;

/**
 * Created by wind on 2017/3/1.
 */

public class PhotoAdapter extends QuickAdapter<UploadPhoto> {
    public PhotoAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, UploadPhoto item) {
        String path=item.getPath();
        int resId=item.getResId();
        ImageView iv=helper.getView(R.id.iv);

        if (TextUtils.isEmpty(path)){
            //AppCompat.setTransitionName(helper.getView(),AppCompat.TRANSITION_NAME_DEFAULT);
            ViewCompat.setTransitionName(helper.getView(),AppCompat.TRANSITION_NAME_DEFAULT);
            Glide.with(context)
                    .load(resId)
                    .into(iv);
        }else {
            ViewCompat.setTransitionName(helper.getView(),path);
            Glide.with(context)
                    .load(path)
                    .into(iv);
        }


    }
}
