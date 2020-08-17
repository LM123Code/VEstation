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
@Document(indexName = "room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @Id
    @TableId(value = "room_id", type = IdType.AUTO)
    @Field(type = FieldType.Integer)
    private Integer roomId;

    /**
     * 房子标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String roomTitle;

    /**
     * 房子描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String roomDesc;

    /**
     * 价格
     */
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal roomPrice;

    /**
     * 发布时间
     */
    @Field(type = FieldType.Date, format = DateFormat.date)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createDate;

    /**
     * 房子地址
     */
    @Field(type = FieldType.Keyword)
    private String address;

    /**
     * 房间图片集地址
     */
    @Field(type = FieldType.Keyword)
    private String roomUrls;

    /**
     * 面积
     */
    @Field(type = FieldType.Integer)
    private Integer roomArea;

    /**
     * 房子类型
     */
    @Field(type = FieldType.Keyword)
    private String roomClass;

    /**
     * 联系方式
     */
    @Field(type = FieldType.Keyword)
    private String contact;
    /**
     * 发布人
     */
    @Field(type = FieldType.Keyword)
    private String openid;

    /**
     * 浏览量
     */
    @Field(type = FieldType.Integer)
    private Integer views;

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


    /**
     * 发布者
     */
    @TableField(exist = false) //数据库不存在的字段
    private VxUser vxUser;
}
