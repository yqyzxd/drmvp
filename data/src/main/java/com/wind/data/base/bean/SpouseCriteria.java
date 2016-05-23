package com.wind.data.base.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shi on 2015/9/21.
 * 择偶条件
 */
public class SpouseCriteria implements Serializable{
    private int id;
    /**
     * 想对你说
     */
    @SerializedName("desc")
    private String sayToU;
    private String age;//是一个范围
    private String high;
    //居住地（生活在）
    private String abode;
    @SerializedName("native_place")
    private String nativePlace;
    //年收入
    @SerializedName("annual_income")
    private String annualIncome;
    //婚姻状况
    @SerializedName("marital_status")
    private String maritalStatus;
    private String job;
    //信仰
    private String belief;
    //饮酒
    private String drink;
    //吸烟
    private String smoke;
    @SerializedName("family_ranking")
    private String famliyRanking;
    //有无子女
    @SerializedName("children_status")
    private String childrenStatus;
    //学历
    private String education;
    //体重
    private String weight;
    //星座
    private String constellation;
    //胸围
    private String bust;
    //腰围
    private String waist;
    //臀部
    private String hips;

    private String planMarryTime;

    public String getPlanMarryTime() {
        return planMarryTime;
    }

    public void setPlanMarryTime(String planMarryTime) {
        this.planMarryTime = planMarryTime;
    }

    public String getSayToU() {
        return sayToU;
    }

    public void setSayToU(String sayToU) {
        this.sayToU = sayToU;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getAbode() {
        return abode;
    }

    public void setAbode(String abode) {
        this.abode = abode;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBelief() {
        return belief;
    }

    public void setBelief(String belief) {
        this.belief = belief;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getFamliyRanking() {
        return famliyRanking;
    }

    public void setFamliyRanking(String famliyRanking) {
        this.famliyRanking = famliyRanking;
    }

    public String getChildrenStatus() {
        return childrenStatus;
    }

    public void setChildrenStatus(String childrenStatus) {
        this.childrenStatus = childrenStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

