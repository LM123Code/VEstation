package com.zhiyi.vestation.pojo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-01
 */
public class VxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String userAvatarUrl;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 公司名称
     */
    private String companyName;

    private String companyUrl;

    /**
     * 岗位信息
     */
    private String profession;

    /**
     * 学校名称
     */
    private String schoolName;

    private String schoolUrl;

    /**
     * 性别，0男，1女
     */
    private Boolean sex;

    /**
     * 所在地
     */
    private String address;

    /**
     * 商品收藏
     */
    private String goodsIds;

    /**
     * 副业收藏
     */
    private String jobIds;

    /**
     * 租房收藏
     */
    private String roomIds;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolUrl() {
        return schoolUrl;
    }

    public void setSchoolUrl(String schoolUrl) {
        this.schoolUrl = schoolUrl;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getJobIds() {
        return jobIds;
    }

    public void setJobIds(String jobIds) {
        this.jobIds = jobIds;
    }

    public String getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(String roomIds) {
        this.roomIds = roomIds;
    }

    @Override
    public String toString() {
        return "VxUser{" +
        "openid=" + openid +
        ", nickName=" + nickName +
        ", userAvatarUrl=" + userAvatarUrl +
        ", phoneNumber=" + phoneNumber +
        ", wechat=" + wechat +
        ", companyName=" + companyName +
        ", companyUrl=" + companyUrl +
        ", profession=" + profession +
        ", schoolName=" + schoolName +
        ", schoolUrl=" + schoolUrl +
        ", sex=" + sex +
        ", address=" + address +
        ", goodsIds=" + goodsIds +
        ", jobIds=" + jobIds +
        ", roomIds=" + roomIds +
        "}";
    }
}
