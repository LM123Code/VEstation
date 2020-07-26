package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

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
@Data
public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "forum_id", type = IdType.AUTO)
    private Integer forumId;

    /**
     * 帖子标题
     */
    private String forumTitle;

    private String forumContent;

    /**
     * 发布人
     */
    private String openid;

    /**
     * 发布时间
     */
    private Date createDate;

    /**
     * 浏览量
     */
    private Integer viewsNum;

    /**
     * 点赞量
     */
    private Integer likeNum;

    /**
     * 帖子类型
     */
    private Integer forumType;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 点赞数
     */
    private Integer likesNum;

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
}
