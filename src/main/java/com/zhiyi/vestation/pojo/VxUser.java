package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId
    private String openid;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真是名称
     */
    private String realName;

    /**
     * 头像地址
     */
    private String userAvatarUrl;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司认证工作证图片
     */
    private String companyUrl;

    /**
     * 岗位信息
     */
    private String profession;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     *学校认证学生证图片
     */
    private String schoolUrl;

    /**
     * 学校专业
     */
    private String schoolMajor;

    /**
     * 性别，0男，1女
     */
    private Boolean sex;

    /**
     * 所在地
     */
    private String address;

    /**
     * 商品收藏
     */
    private String goodsIds;

    /**
     * 副业收藏
     */
    private String jobIds;

    /**
     * 租房收藏
     */
    private String roomIds;

    /**
     * 公司认证，0未认证，1已认证,2未审核，3审核通过，4审核未通过
     */
    private Integer companyExit;

    /**
     * 学校认证，0未认证，1已认证，2未审核，3审核通过，4审核未通过
     */
    private Integer schoolExit;

    /**
     * 是否存在，1存在，0不存在
     */
    private Boolean exist;

    private Long creatTime;

    private Long updateTime;
}
