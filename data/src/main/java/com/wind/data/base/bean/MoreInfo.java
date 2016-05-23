package com.wind.data.base.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wind on 16/3/8.
 */
public class MoreInfo implements Serializable{

    //家庭情况
    @SerializedName( "family_desc")
    private String familyDesc;

    //职业规划
    @SerializedName( "profession_plan")
    private String jobDesc;

    //情感经历
    @SerializedName("emotion_experience")
    private String emotionDesc;


    public String getFamilyDesc() {
        return familyDesc;
    }

    public void setFamilyDesc(String familyDesc) {
        this.familyDesc = familyDesc;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getEmotionDesc() {
        return emotionDesc;
    }

    public void setEmotionDesc(String emotionDesc) {
        this.emotionDesc = emotionDesc;
    }
}
