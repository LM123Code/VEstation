package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.pojo.VxUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
    Map<String, String> login(String appid, String secret, String js_code, String userAvatarUrl, String nickName);

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
    public Status collectForum(String openid, int forumId, int forumType);

    /**
     * 根据openid 和forumType查询出某个用户收藏的某种帖子
     * @param openid
     * @param forumType
     * @return
     */
    public String selectCollect(String openid, int forumType);
}
