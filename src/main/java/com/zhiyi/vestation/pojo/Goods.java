package com.zhiyi.vestation.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@Data
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
    private BigDecimal goodsPrice;

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

    /**
     * 发布者
     */
    @TableField(exist = false) //数据库不存在的字段
    private VxUser vxUser;


}
