package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "commet_id", type = IdType.AUTO)
    private Integer commetId;

    /**
     * 评论人的id
     */
    private String commentOpenid;

    /**
     * 评论类型。0帖子，1二手商品，2租房商品，3副业商品
     */
    private Integer commentClass;

    /**
     * 帖子、二手商品、租房、副业id
     */
    private Integer forumId;

    /**
     * 评论的级别   为-1为一级评论，其他为回复的openid
     */
    private String replyId;

    /**
     * 评论或回复的内容
     */
    private String commentContent;

    /**
     * 评论或者回复的时间
     */
    private Date createDate;

    /**
     * 查看表示，0未查看，1已查看
     */
    private Boolean look;

    /**
     * 是否存在，1存在，0不存在
     */
    private Boolean exit;


    /**
     * 发布者
     */
    @TableField(exist = false) //数据库不存在的字段
    private VxUser vxUser;
}
