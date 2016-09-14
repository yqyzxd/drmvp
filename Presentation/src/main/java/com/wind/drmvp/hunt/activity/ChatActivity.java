package com.wind.drmvp.hunt.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.wind.drmvp.R;

/**
 * Created by wind on 16/9/12.
 */

public class ChatActivity extends FragmentActivity {

    public static final String EXTRA_KEY_NAME = "extra_key_name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        TextView tv= (TextView) findViewById(R.id.tv);
        tv.setText(getIntent().getStringExtra(EXTRA_KEY_NAME));
    }
}
