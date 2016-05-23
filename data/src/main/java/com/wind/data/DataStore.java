package com.wind.data;

import com.wind.data.base.BaseRequest;
import com.wind.data.base.BaseResponse;

import rx.Observable;

/**
 * Created by wind on 16/5/20.
 */
public interface DataStore<Q extends BaseRequest,R extends BaseResponse> {
    Observable<R> get(Q request);
}