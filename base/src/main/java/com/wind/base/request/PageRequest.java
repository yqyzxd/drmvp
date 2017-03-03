package com.wind.base.request;

/**
 * Created by wind on 2017/2/27.
 */

public class PageRequest extends BaseRequest {
    /**
     * 是否是第一页
     */
    private boolean isFirstPage;
    /**
     * 开始页码
     */
    private int startPageIndex;
    /**
     * 每页大小
     */
    private int pageSize;

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }
}
