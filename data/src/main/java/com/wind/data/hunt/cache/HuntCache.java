package com.wind.data.hunt.cache;

import com.wind.data.base.BaseResponse;
import com.wind.data.base.cache.ICache;

import java.io.File;

import rx.Observable;

/**
 * Created by wind on 16/5/20.
 */
public class HuntCache implements ICache {
    @Override
    public Observable<BaseResponse> get() {
        return null;
    }

    @Override
    public void put(BaseResponse response) {

    }

    @Override
    public boolean isCached() {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {

    }

    @Override
    public void evictFile(File file) {

    }
}
