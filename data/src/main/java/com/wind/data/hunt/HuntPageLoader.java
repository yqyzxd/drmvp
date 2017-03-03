package com.wind.data.hunt;

import com.wind.base.http.loader.PageLoader;
import com.wind.base.http.page.IPage;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;

import javax.inject.Inject;

/**
 * Created by wind on 2017/2/27.
 */

public class HuntPageLoader extends PageLoader<HuntRequest,HuntResponse> {
    @Inject
    public HuntPageLoader(IPage<HuntRequest,HuntResponse> page) {
        super(page);
    }
}
