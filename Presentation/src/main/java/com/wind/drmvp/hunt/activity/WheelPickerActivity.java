package com.wind.drmvp.hunt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.aigestudio.wheelpicker.utils.WheelPickerFactory;
import com.aigestudio.wheelpicker.widget.IWheelVo;
import com.wind.drmvp.R;

/**
 * Created by wind on 2017/3/3.
 */

public class WheelPickerActivity extends FragmentActivity implements WheelPickerFactory.OnWheelClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wheel);
    }


    public void show(View v){
        String defaultA = "";
        String defaultB = "";


        WheelPickerFactory.showWheelAAPicker(v, this, R.xml.wheel_location, defaultA, defaultB, false);
    }

    @Override
    public void onResult(View v, IWheelVo[] result, int[] indexs, String[] unit) {
        Log.e("Wheel",result[0].getLabel());
    }
}
