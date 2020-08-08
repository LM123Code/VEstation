package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.pojo.VxUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.elasticsearch.common.recycler.Recycler;


import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-01
 */
public interface VxUserService extends IService<VxUser> {

    /**
     * 登录方法
     *
     * @param appid 小程序唯一标识
     * @param secret
     * @param js_code 登陆凭证
     * @param userAvatarUrl 头像地址
     * @param nickName 昵称
     * @return
     */
    ResultStatus login(String appid, String secret, String js_code, String userAvatarUrl, String nickName);

    /**
     * 获取vxUser的指定属性
     * @return vxUser对象
     */
    VxUser selectByWrapper(String openid);

    /**
     * 收藏帖子
     * @param openid   用户标识
     * @param forumId  帖子标识
     * @param forumType  帖子类型
     * @return
     */
    public ResultStatus collectForum(String openid, int forumId, int forumType);

    /**
     * 查询某用户的收藏的帖子，用于我的动态展示
     * @param openid
     * @param forumType
     * @return
     */
    public List selectCollectsOfUser(String openid, int forumType);
    /**
     * 根据openid 和forumType查询出某个用户收藏的某种帖子  用于查询用户的对帖子的收藏状态
     * @param openid
     * @param forumType
     * @return
     */
    public String selectCollect(String openid, int forumType);

    /**
     * 根据openid查询用户的所有信息
     * @param openid
     * @return
     */
    public ResultStatus selectUserInfo(String openid);


    /**
     * 更新用户的信息
     * @param vxUser
     * @return
     */
    public ResultStatus updateUserInfo(VxUser vxUser);

    /**
     * 查询是否学生身份认证
     * @param openid
     * @return
     */
    public Map<String,String> selectStudentCertification(String openid);

    /**
     * 查询是否职工身份认真
     * @param openid
     * @return
     */
    public Map<String,String> selectStaffCertification(String openid);
}
