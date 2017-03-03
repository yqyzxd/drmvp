package com.wind.data.hunt;

import com.wind.base.api.IPageApi;
import com.wind.base.http.page.PsPage;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;

import javax.inject.Inject;

/**
 * Created by wind on 2017/2/27.
 */

public class HuntPage extends PsPage<HuntRequest,HuntResponse> {
    @Inject
    public HuntPage(IPageApi api) {
        super(api);
    }
}
