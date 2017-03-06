package com.wind.drmvp.hunt.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.SparseArray;
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
    private SparseArray<Object> tags;
    public PhotoAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
        tags=new SparseArray();
    }

    @Override
    protected void convert(BaseAdapterHelper helper, UploadPhoto item) {
        String path=item.getPath();
        int resId=item.getResId();
        ImageView iv=helper.getView(R.id.iv);
        if (TextUtils.isEmpty(path)){

            ViewCompat.setTransitionName(iv, AppCompat.TRANSITION_NAME_DEFAULT);
            Glide.with(context)
                    .load(resId)
                    .into(iv);
        }else {
           // iv.setTag(R.id.imageid,path);
           ViewCompat.setTransitionName(iv,path);
            Glide.with(context)
                    .load(path)
                    .into(iv);
        }
        tags.put(helper.getPosition(),iv.getTag());

    }

    public Object getChildTag(int position) {
        return tags.get(position);
    }
}
