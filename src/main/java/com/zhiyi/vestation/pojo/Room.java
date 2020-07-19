package com.zhiyi.vestation.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    /**
     * 职位标题
     */
    private String roomTitle;

    /**
     * 职位要求
     */
    private String rootDesc;

    /**
     * 价格
     */
    private BigDecimal roomPrise;

    /**
     * 发布时间
     */
    private Date createDate;

    /**
     * 房子地址
     */
    private String address;

    /**
     * 房间图片集地址
     */
    private String roomUrls;

    /**
     * 面积
     */
    private Integer roomArea;

    /**
     * 房子类型
     */
    private String roomClass;

    /**
     * 发布人
     */
    private String openid;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 推荐分数
     */
    private Integer score;

    /**
     * 是否存在,0不存在，1存在
     */
    @TableLogic
    private Boolean exist;


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRootDesc() {
        return rootDesc;
    }

    public void setRootDesc(String rootDesc) {
        this.rootDesc = rootDesc;
    }

    public BigDecimal getRoomPrise() {
        return roomPrise;
    }

    public void setRoomPrise(BigDecimal roomPrise) {
        this.roomPrise = roomPrise;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomUrls() {
        return roomUrls;
    }

    public void setRoomUrls(String roomUrls) {
        this.roomUrls = roomUrls;
    }

    public Integer getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Integer roomArea) {
        this.roomArea = roomArea;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "Room{" +
        "roomId=" + roomId +
        ", roomTitle=" + roomTitle +
        ", rootDesc=" + rootDesc +
        ", roomPrise=" + roomPrise +
        ", createDate=" + createDate +
        ", address=" + address +
        ", roomUrls=" + roomUrls +
        ", roomArea=" + roomArea +
        ", roomClass=" + roomClass +
        ", openid=" + openid +
        ", views=" + views +
        ", score=" + score +
        ", exist=" + exist +
        "}";
    }
}
