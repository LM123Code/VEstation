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
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "job_id", type = IdType.AUTO)
    private Integer jobId;

    /**
     * 职位标题
     */
    private String jobTitle;

    /**
     * 职位分类
     */
    private String jobClass;

    /**
     * 职位要求
     */
    private String jobDesc;

    /**
     * 价格
     */
    private BigDecimal jobPrise;

    /**
     * 发布时间
     */
    private Date createDate;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 工作地址
     */
    private String address;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 发布人
     */
    private String openid;

    /**
     * 投递邮箱
     */
    private String email;

    /**
     * 简历格式
     */
    private String resumeFormat;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 推荐分数
     */
    private Integer score;

    /**
     * 是否存在,0不存在，1存在
     */
    @TableLogic
    private Boolean exist;


    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public BigDecimal getJobPrise() {
        return jobPrise;
    }

    public void setJobPrise(BigDecimal jobPrise) {
        this.jobPrise = jobPrise;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResumeFormat() {
        return resumeFormat;
    }

    public void setResumeFormat(String resumeFormat) {
        this.resumeFormat = resumeFormat;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
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
        return "Job{" +
        "jobId=" + jobId +
        ", jobTitle=" + jobTitle +
        ", jobClass=" + jobClass +
        ", jobDesc=" + jobDesc +
        ", jobPrise=" + jobPrise +
        ", createDate=" + createDate +
        ", companyName=" + companyName +
        ", address=" + address +
        ", views=" + views +
        ", openid=" + openid +
        ", email=" + email +
        ", resumeFormat=" + resumeFormat +
        ", wechat=" + wechat +
        ", score=" + score +
        ", exist=" + exist +
        "}";
    }
}
