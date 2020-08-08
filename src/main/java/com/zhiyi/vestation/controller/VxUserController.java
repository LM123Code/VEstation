package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Forum;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResultStatus login(String appid, String secret,String js_code, String userAvatarUrl, String nickName){
        return vxUserService.login(appid, secret, js_code, userAvatarUrl, nickName);
    }

    /**
     * 根据用户openid查询用户信息
     * @param openid
     * @return
     */
    @GetMapping("/userInfo")
    public ResultStatus selectUserInfo(String openid) {
        return vxUserService.selectUserInfo(openid);
    }

    /**
     * 更新用户信息
     * @param vxUser
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public ResultStatus updateUserInfo(VxUser vxUser) {
        return vxUserService.updateUserInfo(vxUser);
    }



    /**
     * 收藏帖子
     * @param openid  用户openid
     * @param forumId   帖子id
     * @param forumType  帖子类型
     * @return
     */
    @GetMapping("/collect")
    public ResultStatus collectForum(String openid, int forumId, int forumType) {
        return vxUserService.collectForum(openid,forumId,forumType);
    }

    /**
     * 查询用户收藏
     * @param openid
     * @param forumType
     * @return
     */
    @RequestMapping("/selectCollect")
    public ResultStatus selectCollect(String openid, int forumType) {
        List<Forum> list = vxUserService.selectCollectsOfUser(openid, forumType);
        ResultStatus resultStatus = new ResultStatus();
        if (openid == null || openid.equals("") || forumType <= 0) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }else if (list == null || list.size() == 0) {
            return resultStatus.setCode("1").setMsg("没有数据");
        }else {
            return resultStatus.setMsg("ok").setCode("200").setData(list);
        }
    }

    /**
     * 查询学生身份认证
     * @param openid
     * @return
     */
    @GetMapping("/selectStudentCertification")
    public ResultStatus selectStudentCertification(String openid) {
        Map<String, String> certificationMap = vxUserService.selectStudentCertification(openid);
        if (openid == null || openid.equals("")) {
            return ResultStatus.builder().code("0").msg("参数异常").build();
        }else if (certificationMap == null || certificationMap.size() == 0) {
            return ResultStatus.builder().code("1").msg("查询异常，没有装填").build();
        }else {
            return ResultStatus.builder().code("200").msg("ok").data(certificationMap).build();
        }
    }

    /**
     * 查询用户是否职工认证
     * @param openid
     * @return
     */
    @GetMapping("/selectStaffCertification")
    public ResultStatus selectStaffCertification(String openid) {
        Map<String, String> certificationMap = vxUserService.selectStaffCertification(openid);
        if (openid == null || openid.equals("")) {
            return ResultStatus.builder().code("0").msg("参数异常").build();
        }else if (certificationMap == null || certificationMap.size() == 0) {
            return ResultStatus.builder().code("1").msg("查询异常，没有装填").build();
        }else {
            return ResultStatus.builder().code("200").msg("ok").data(certificationMap).build();
        }
    }
}

