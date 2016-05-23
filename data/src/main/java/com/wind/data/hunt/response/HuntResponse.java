package com.wind.data.hunt.response;

import com.google.gson.annotations.SerializedName;
import com.wind.data.base.BaseResponse;
import com.wind.data.hunt.bean.Hunt;

/**
 * Created by wind on 16/5/20.
 */
public class HuntResponse extends BaseResponse {
    @SerializedName("items")
    private Hunt hunt;

    public Hunt getHunt() {
        return hunt;
    }

    public void setHunt(Hunt hunt) {
        this.hunt = hunt;
    }
}