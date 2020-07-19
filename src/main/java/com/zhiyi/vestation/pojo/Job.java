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


    /**
     * 发布者
     */
    @TableField(exist = false) //数据库不存在的字段
    private VxUser vxUser;
}
