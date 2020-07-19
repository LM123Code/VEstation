package com.zhiyi.vestation.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Integer feedbackId;

    /**
     * 反馈类型，0bug反馈，1商业合作
     */
    private Boolean feedClass;

    /**
     * 反馈内容
     */
    private String feedContent;

    /**
     * 反馈时间
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


    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Boolean getFeedClass() {
        return feedClass;
    }

    public void setFeedClass(Boolean feedClass) {
        this.feedClass = feedClass;
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
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

    public Boolean getExit() {
        return exit;
    }

    public void setExit(Boolean exit) {
        this.exit = exit;
    }

    @Override
    public String toString() {
        return "Feedback{" +
        "feedbackId=" + feedbackId +
        ", feedClass=" + feedClass +
        ", feedContent=" + feedContent +
        ", createDate=" + createDate +
        ", look=" + look +
        ", exit=" + exit +
        "}";
    }
}
