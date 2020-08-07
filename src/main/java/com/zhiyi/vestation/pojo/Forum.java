package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@Document(indexName = "forum")
@Data
public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @Id
    @Field(type = FieldType.Integer)
    @TableId(value = "forum_id", type = IdType.AUTO)
    private Integer forumId;

    /**
     * 帖子标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String forumTitle;

    /**
     * 帖子内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String forumContent;

    /**
     * 发布人
     */
    @Field(type = FieldType.Keyword)
    private String openid;

    /**
     * 发布时间
     */
    
    @Field(type = FieldType.Date, format = DateFormat.date)
    private Date createDate;

    /**
     * 浏览量
     */
    @Field(type = FieldType.Integer)
    private Integer views;

    /**
     * 点赞量
     */
    @Field(type = FieldType.Integer)
    private Integer likeNum;

    /**
     * 帖子类型
     */
    @Field(type = FieldType.Integer)
    private Integer forumType;

    /**
     * 评论数
     */
    @Field(type = FieldType.Integer)
    private Integer commentNum;


    /**
     * 是否存在，1存在，0不存在
     */
    @TableLogic
    private Boolean exist;


    /**
     * 发布者
     */
    @TableField(exist = false) //数据库不存在的字段
    private VxUser vxUser;


    /**
     * 评论
     */
    @TableField(exist =  false)
    private List<Comment> comments;

    /**
     * 是否被点攒状态
     */
    @TableField(exist =  false)
    private Boolean likeStatus;

    /**
     * 是否被收藏状态
     */
    @TableField(exist = false)
    private Boolean collectStatus;
}
