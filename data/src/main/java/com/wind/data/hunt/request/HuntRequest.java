package com.wind.data.hunt.request;

import com.wind.data.base.BaseRequest;

/**
 * Created by wind on 16/5/20.
 */
public class HuntRequest extends BaseRequest {


    private int page;
    private int count;

    private boolean isFirstPage;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }
}
