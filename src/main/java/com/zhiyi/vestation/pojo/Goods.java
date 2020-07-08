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
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 商品图片集地址
     */
    private String goodsUrls;

    /**
     * 价格
     */
    private BigDecimal goodsPrise;

    /**
     * 发布地址
     */
    private String submitAddress;

    /**
     * 发布时间
     */
    private Date createDate;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 发布人
     */
    private String openid;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 推荐分数
     */
    private Integer score;

    /**
     * 是否存在,0不存在，1存在
     */
    @TableLogic
    private Boolean exist;


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsUrls() {
        return goodsUrls;
    }

    public void setGoodsUrls(String goodsUrls) {
        this.goodsUrls = goodsUrls;
    }

    public BigDecimal getGoodsPrise() {
        return goodsPrise;
    }

    public void setGoodsPrise(BigDecimal goodsPrise) {
        this.goodsPrise = goodsPrise;
    }

    public String getSubmitAddress() {
        return submitAddress;
    }

    public void setSubmitAddress(String submitAddress) {
        this.submitAddress = submitAddress;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        return "Goods{" +
        "goodsId=" + goodsId +
        ", goodsTitle=" + goodsTitle +
        ", goodsDesc=" + goodsDesc +
        ", goodsUrls=" + goodsUrls +
        ", goodsPrise=" + goodsPrise +
        ", submitAddress=" + submitAddress +
        ", createDate=" + createDate +
        ", views=" + views +
        ", openid=" + openid +
        ", contact=" + contact +
        ", score=" + score +
        ", exist=" + exist +
        "}";
    }
}
