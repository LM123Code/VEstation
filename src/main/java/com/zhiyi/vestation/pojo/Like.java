package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "like_id", type = IdType.AUTO)
    private Integer likeId;

    /**
     * 被点赞的帖子
     */
    private Integer forumId;

    /**
     * 点赞人
     */
    private String openid;

    /**
     * 收藏时间
     */
    private Date createDate;

    /**
     * 查看表示，0未查看，1已查看
     */
    private Boolean look;


    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getLook() {
        return look;
    }

    public void setLook(Boolean look) {
        this.look = look;
    }

    @Override
    public String toString() {
        return "Like{" +
        "likeId=" + likeId +
        ", forumId=" + forumId +
        ", openid=" + openid +
        ", createDate=" + createDate +
        ", look=" + look +
        "}";
    }
}
