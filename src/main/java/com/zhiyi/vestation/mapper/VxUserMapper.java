package com.zhiyi.vestation.mapper;

import com.zhiyi.vestation.pojo.VxUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-07-01
 */
@Mapper
public interface VxUserMapper extends BaseMapper<VxUser> {

    /**
     * 根据openid、昵称、头像信息检索用户，当openid存在时更新昵称和头像，不存在是插入新用户openid、昵称、头像
     * @param openid 微信唯一标识
     * @param nickName 昵称
     * @param userAvatarUrl 头像url路径
     * @return
     */

    int insertOrUpdateByOpenid(String openid, String nickName, String userAvatarUrl);

}
