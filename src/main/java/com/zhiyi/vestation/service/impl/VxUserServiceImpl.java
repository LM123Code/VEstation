package com.zhiyi.vestation.service.impl;

import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.mapper.VxUserMapper;
import com.zhiyi.vestation.service.VxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-01
 */
@Service
public class VxUserServiceImpl extends ServiceImpl<VxUserMapper, VxUser> implements VxUserService {

    @Autowired
    VxUserMapper vxUserMapper;//注入为微信user的mapper类

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
    @Override
    public Map<String, String> login(String appid, String secret, String js_code, String userAvatarUrl, String nickName) {
        Map map = new HashMap<>();
        //登录凭证不能为空
        if (js_code == null || js_code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = appid;
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = secret;
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数：唯一标识+秘钥+登录凭证
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + js_code ;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的微信唯一标识（openid）
        String openid = (String) json.get("openid");

//        插入或更新用户
        int status = vxUserMapper.insertOrUpdateByOpenid(openid, nickName, userAvatarUrl);


        map.put("status", status); //登录成功status为1，失败为0
        map.put("openid",openid);

        return map;
    }
}
