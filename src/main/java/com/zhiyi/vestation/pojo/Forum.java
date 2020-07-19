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
    private Boolean exit;


    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getForumContent() {
        return forumContent;
    }

    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
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

    public Integer getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(Integer viewsNum) {
        this.viewsNum = viewsNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getForumType() {
        return forumType;
    }

    public void setForumType(Integer forumType) {
        this.forumType = forumType;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum) {
        this.likesNum = likesNum;
    }

    public Boolean getExit() {
        return exit;
    }

    public void setExit(Boolean exit) {
        this.exit = exit;
    }

    @Override
    public String toString() {
        return "Forum{" +
        "forumId=" + forumId +
        ", forumTitle=" + forumTitle +
        ", forumContent=" + forumContent +
        ", openid=" + openid +
        ", createDate=" + createDate +
        ", viewsNum=" + viewsNum +
        ", likeNum=" + likeNum +
        ", forumType=" + forumType +
        ", commentNum=" + commentNum +
        ", likesNum=" + likesNum +
        ", exit=" + exit +
        "}";
    }
}
