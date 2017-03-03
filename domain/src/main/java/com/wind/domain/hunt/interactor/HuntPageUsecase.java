package com.wind.domain.hunt.interactor;

import com.wind.base.http.loader.PageLoader;
import com.wind.base.usecase.PageUsecase;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;

import javax.inject.Inject;

/**
 * Created by wind on 2017/2/27.
 */

public class HuntPageUsecase extends PageUsecase<HuntRequest,HuntResponse> {
    @Inject
    public HuntPageUsecase(PageLoader<HuntRequest,HuntResponse> pageLoader) {
        super(pageLoader);
    }
}
