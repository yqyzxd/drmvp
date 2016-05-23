package com.wind.data.base.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sh on 2015/10/9.
 */
public class BaseUserInfo implements Serializable {
    private int id;
    private String uid;

    private String name;
    //0男1女
    private int gender=-1;
    private String age;
    private String birthday;
    private String high;
    @SerializedName("annual_income")
    private String annualIncome;
    @SerializedName("create_time")
    private long createTime;
    private String abode;
    @SerializedName("plan_marry_time")
    private String planMarryTime;


    //名族
    private String nation;
    private String phone;

    private String token;
    //最高学历
    private String education;

    @SerializedName("native_place")
    private String native_place;

    @SerializedName("marital_status")
    private String maritalStatus;

    private String job;
    private String belief;


    private String drink;

    private String smoke;
    @SerializedName("family_ranking")
    private String familyRanking;

    @SerializedName("children_status")
    private String childrenStatus;

    private String weight;
    private String constellation;


    private Photo avatar;
    @SerializedName("about_me")
    private String aboutMe;

    @SerializedName("house_status")
    private String houseStatus;


    @SerializedName("house_number")
    private int houseNumber;

    @SerializedName("car_status")
    private String carStatus;

    @SerializedName("car_number")
    private int carNumber;
   // private int isIdentityAuth;

    private ArrayList<String> tags;


    //照片列表
    private List<Photo> photos;
    //封面
    private Photo cover;

    @SerializedName("photo_count")
    private  int photoCount;


    //是否关闭了资料


    //更多信息,只在提交信息时用
    private String familyDesc;
    private String jobPlan;
    private String emotionExperience;

    public String getFamilyDesc() {
        return familyDesc;
    }

    public void setFamilyDesc(String familyDesc) {
        this.familyDesc = familyDesc;
    }

    public String getJobPlan() {
        return jobPlan;
    }

    public void setJobPlan(String jobPlan) {
        this.jobPlan = jobPlan;
    }

    public String getEmotionExperience() {
        return emotionExperience;
    }

    public void setEmotionExperience(String emotionExperience) {
        this.emotionExperience = emotionExperience;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getAbode() {
        return abode;
    }

    public void setAbode(String abode) {
        this.abode = abode;
    }

    public String getPlanMarryTime() {
        return planMarryTime;
    }

    public void setPlanMarryTime(String planMarryTime) {
        this.planMarryTime = planMarryTime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place;
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

    public String getFamilyRanking() {
        return familyRanking;
    }

    public void setFamilyRanking(String familyRanking) {
        this.familyRanking = familyRanking;
    }

    public String getChildrenStatus() {
        return childrenStatus;
    }

    public void setChildrenStatus(String childrenStatus) {
        this.childrenStatus = childrenStatus;
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

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }



    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Photo getCover() {
        return cover;
    }

    public void setCover(Photo cover) {
        this.cover = cover;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
