package com.wind.data.base.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sh on 2015/10/9.
 */
public class Status implements Serializable {

    private int id;
    //是否在线 0 否 1 是
    private int online;
    //个人信息中的字段
    @SerializedName("like_count")
    private int likeCount;
    //关注我
    @SerializedName("liked_count")//ta人信息中的被关注数
    private int likedCount;
    //资料完整性
    private int integrity;

    //头像审核状态 0正常，-1未通过 2审核中
    @SerializedName("avatar_verify_status")
    private int avatarVerifyStatus;

    //用户列表中的字段
    private int liked;//是否关注他
    @SerializedName("is_like")
    private int isLike;//是否关注
    @SerializedName("liked_me")
    private int likedMe;
    @SerializedName("like_to_like")
    private int likeToLike;

    //是否是推荐用户 0否1是
    private int recommended;



    private int photo_count;


//认证付费状态 0未付费 1已付费
    private int auth_fees_status;
    //魅力说状态 0不完整 1完整
    private int charm_status;
    //基本信息认证状态 0不完整 1完整
    private int info_status;
    //会员收费状态 0未付费 1已付费
    private int member_fees_status;

    //查看在线状态 0没有1有
    private int view_online_status;

    /** 是否需要锁定
     * 1不锁定
     * 0锁定
     */

    private int if_lock;


    /**
     * 必填资料是否已填全,0否1是
     *
     */
    @SerializedName( "completeMineInfo")
    private int completeMineInfo;




    //0非会员 -1会员已过期   >1剩余会员天数
    private int member_day;

    public int getCompleteMineInfo() {
        return completeMineInfo;
    }

    public void setCompleteMineInfo(int completeMineInfo) {
        this.completeMineInfo = completeMineInfo;
    }

    public int getMember_day() {
        return member_day;
    }

    public void setMember_day(int member_day) {
        this.member_day = member_day;
    }



    public int getIf_lock() {
        return if_lock;
    }

    public void setIf_lock(int if_lock) {
        this.if_lock = if_lock;
    }

    public int getAuth_fees_status() {
        return auth_fees_status;
    }

    public void setAuth_fees_status(int auth_fees_status) {
        this.auth_fees_status = auth_fees_status;
    }

    public int getCharm_status() {
        return charm_status;
    }

    public void setCharm_status(int charm_status) {
        this.charm_status = charm_status;
    }

    public int getInfo_status() {
        return info_status;
    }

    public void setInfo_status(int info_status) {
        this.info_status = info_status;
    }

    public int getMember_fees_status() {
        return member_fees_status;
    }

    public void setMember_fees_status(int member_fees_status) {
        this.member_fees_status = member_fees_status;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(int likedCount) {
        this.likedCount = likedCount;
    }

    public int getIntegrity() {
        return integrity;
    }

    public void setIntegrity(int integrity) {
        this.integrity = integrity;
    }

    public int getAvatarVerifyStatus() {
        return avatarVerifyStatus;
    }

    public void setAvatarVerifyStatus(int avatarVerifyStatus) {
        this.avatarVerifyStatus = avatarVerifyStatus;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getLikedMe() {
        return likedMe;
    }

    public void setLikedMe(int likedMe) {
        this.likedMe = likedMe;
    }

    public int getLikeToLike() {
        return likeToLike;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public void setLikeToLike(int likeToLike) {
        this.likeToLike = likeToLike;
    }


    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPhoto_count() {
        return photo_count;
    }

    public void setPhoto_count(int photo_count) {
        this.photo_count = photo_count;
    }

    public int getView_online_status() {
        return view_online_status;
    }

    public void setView_online_status(int view_online_status) {
        this.view_online_status = view_online_status;
    }
}
