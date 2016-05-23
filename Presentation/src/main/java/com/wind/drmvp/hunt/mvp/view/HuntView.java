package com.wind.drmvp.hunt.mvp.view;

import com.wind.data.hunt.response.HuntResponse;
import com.wind.drmvp.user.view.UserView;

/**
 * Created by wind on 16/5/20.
 */
public interface HuntView extends UserView {
    void showUsers(HuntResponse response);
}
