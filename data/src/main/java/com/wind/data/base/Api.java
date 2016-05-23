package com.wind.data.base;

import rx.Observable;

/**
 * Created by wind on 16/5/20.
 */
public interface Api<Q extends BaseRequest,R extends BaseRequest> {

    Observable<R> get(Q request);
}
