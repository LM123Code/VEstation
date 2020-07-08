package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "ad_id", type = IdType.AUTO)
    private Integer adId;

    /**
     * 广告图片地址
     */
    private String imageUrl;

    /**
     * 广告链接地址
     */
    private String linkUrl;

    /**
     * 是否存在,0不存在，1存在
     */
    @TableLogic
    private Boolean exist;


    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "Ad{" +
        "adId=" + adId +
        ", imageUrl=" + imageUrl +
        ", linkUrl=" + linkUrl +
        ", exist=" + exist +
        "}";
    }
}
