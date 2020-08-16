package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

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
@Data
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Integer feedbackId;

    /**
     * 反馈人
     */
    private String openid;

    /**
     * 反馈类型，0bug反馈，1商业合作
     */
    private Boolean feedClass;

    /**
     * 反馈内容
     */
    private String feedContent;

    /**
     * 反馈图片
     */
    private String feedImages;

    /**
     * 反馈时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createDate;

    /**
     * 反馈人联系电话
     */
    private String phoneNumber;

    /**
     * 查看表示，0未查看，1已查看
     */
    private Boolean look;

    /**
     * 是否存在，1存在，0不存在
     */
    private Boolean exist;


    /**
     * 发布者
     */
    @TableField(exist = false) //数据库不存在的字段
    private VxUser vxUser;
}
