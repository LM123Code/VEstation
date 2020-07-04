package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-07-01
 */
@RestController
@RequestMapping("${api-url}/vx-user")
public class VxUserController {

    @Autowired
    VxUserService vxUserService;//注入微信用户的service对象

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
    @ResponseBody //将返回对象转化为json数据，写入response对象的body区
    @PostMapping("/login") //访问路径
    public Map<String, String> login(String appid, String secret,String js_code, String userAvatarUrl, String nickName){
        return vxUserService.login(appid, secret, js_code, userAvatarUrl, nickName);
    }

}
