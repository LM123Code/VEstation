package com.zhiyi.vestation.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @Id
    @Field(type = FieldType.Integer)
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String goodsTitle;

    /**
     * 商品描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String goodsDesc;

    /**
     * 商品图片集地址
     */
    @Field(type = FieldType.Keyword)
    private String goodsUrls;

    /**
     * 价格
     */
    @Field(type = FieldType.Scaled_Float,scalingFactor = 100)
    private BigDecimal goodsPrice;

    /**
     * 发布地址
     */
    @Field(type = FieldType.Keyword)
    private String submitAddress;

    /**
     * 发布时间
     */
    @Field(type = FieldType.Date,format = DateFormat.date)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createDate;

    /**
     * 浏览量
     */
    @Field(type = FieldType.Integer)
    private Integer views;

    /**
     * 发布人
     */
    @Field(type = FieldType.Keyword)
    private String openid;

    /**
     * 联系方式
     */
    @Field(type = FieldType.Keyword)
    private String contact;

    /**
     * 推荐分数
     */
    @Field(type = FieldType.Integer)
    private Integer score;

    /**
     * 是否存在,0不存在，1存在
     */
    @TableLogic
    private Boolean exist;


    private String goodsType;

    /**
     * 是否存在,0旧，1新的
     */
    private String isNew;

    /**
     * 发布者
     */
    @TableField(exist = false) //数据库不存在的字段
    private VxUser vxUser;


}
