package com.wind.base.api;

import com.wind.base.request.PageRequest;
import com.wind.base.response.PageResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by wind on 2017/2/27.
 */

public interface IPageApi<Q extends PageRequest,R extends PageResponse> extends IRetrieveApi<Q,R> {


    Observable<R> get(Map<String,String> fieldMap);
}
