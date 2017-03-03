package com.wind.data.base.cache;


import com.wind.base.response.BaseResponse;

import java.io.File;

import rx.Observable;

/**
 * Created by wind on 16/4/29.
 */
public interface ICache {

   Observable<BaseResponse> get();


    void put(BaseResponse response);


    boolean isCached();

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();


    void evictFile(File file);
}
