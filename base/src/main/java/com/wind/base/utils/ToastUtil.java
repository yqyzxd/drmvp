package com.wind.base.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wind on 16/5/19.
 */
public class ToastUtil {

    public static void showToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void showToast(Context context,int stringId){
        Toast.makeText(context,stringId,Toast.LENGTH_SHORT).show();
    }
}
