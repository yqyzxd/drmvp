package com.wind.drmvp.hunt.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.wind.base.di.HasComponent;
import com.wind.drmvp.R;
import com.wind.drmvp.base.App;
import com.wind.drmvp.base.BaseActivity;
import com.wind.drmvp.hunt.di.UploadPhotosComponent;
import com.wind.drmvp.hunt.di.UploadPhotosModule;
import com.wind.drmvp.hunt.mvp.view.impl.UploadPhotosFragment;

import java.util.List;
import java.util.Map;

import static com.wind.drmvp.base.activity.PhotoPreviewActivity.EXTRA_KEY_CURRENT_POSITION;
import static com.wind.drmvp.base.activity.PhotoPreviewActivity.EXTRA_KEY_START_POSITION;

/**
 * Created by sh on 2015/10/9.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class UploadPhotosActivity extends BaseActivity implements HasComponent<UploadPhotosComponent> {

    private UploadPhotosComponent mComponent;
    private Bundle mTmpReenterState;

    private final SharedElementCallback mCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (mTmpReenterState != null) {
                int startingPosition = mTmpReenterState.getInt(EXTRA_KEY_START_POSITION);
                int currentPosition = mTmpReenterState.getInt(EXTRA_KEY_CURRENT_POSITION);
                if (startingPosition != currentPosition) {
                    // If startingPosition != currentPosition the user must have swiped to a
                    // different page in the DetailsActivity. We must update the shared element
                    // so that the correct one falls into place.
                    Object tag=mFragment.getChindTag(currentPosition+1);
                    View viewParent =mFragment.findTransitionViewByTag(tag);
                    View newSharedElement=viewParent.findViewById(R.id.iv);
                    String newTransitionName=ViewCompat.getTransitionName(newSharedElement);
                    if (newSharedElement != null) {
                        names.clear();
                        names.add(newTransitionName);
                        sharedElements.clear();
                        sharedElements.put(newTransitionName, newSharedElement);
                    }
                }

                mTmpReenterState = null;
            } else {
                // If mTmpReenterState is null, then the activity is exiting.
                View navigationBar = findViewById(android.R.id.navigationBarBackground);
                View statusBar = findViewById(android.R.id.statusBarBackground);
                if (navigationBar != null) {
                    names.add(navigationBar.getTransitionName());
                    sharedElements.put(navigationBar.getTransitionName(), navigationBar);
                }
                if (statusBar != null) {
                    names.add(statusBar.getTransitionName());
                    sharedElements.put(statusBar.getTransitionName(), statusBar);
                }
            }
        }
    };
    private UploadPhotosFragment mFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        ActivityCompat.setExitSharedElementCallback(this,mCallback);
        mFragment=UploadPhotosFragment.getInstance();
        replaceFragment(mFragment);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        mTmpReenterState = new Bundle(data.getExtras());
        mFragment.onActivityReenter(resultCode,data);


    }

    @Override
    protected void initializeInjector() {
        mComponent = App.get()
                .appComponent()
                .plus(new UploadPhotosModule(this));
    }

    @Override
    public UploadPhotosComponent getComponent() {
        return mComponent;
    }

}
