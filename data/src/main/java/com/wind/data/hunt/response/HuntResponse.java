package com.wind.data.hunt.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.wind.base.response.PageResponse;
import com.wind.data.hunt.bean.Hunt;

/**
 * Created by wind on 16/5/20.
 */
public class HuntResponse extends PageResponse {
    @SerializedName("items")
    @JSONField(name = "items")
    private Hunt hunt;



    public Hunt getHunt() {
        return hunt;
    }

    public void setHunt(Hunt hunt) {
        this.hunt = hunt;
    }


}
