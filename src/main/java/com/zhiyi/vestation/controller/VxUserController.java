package com.zhiyi.vestation.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.service.VxUserService;
import com.zhiyi.vestation.utils.HttpRequest;
import kotlin.Result;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Autowired
    HttpSession httpSession;



    /**
     * 登录方法
     *
     * @param loginPojo 登录信息对象
     * @return
     */

//    @PostMapping("/login") //访问路径
//    public ResultStatus login(String appid, String secret,String js_code, String userAvatarUrl, String nickName){
//        return vxUserService.login(appid, secret, js_code, userAvatarUrl, nickName);
//    }

    @PostMapping("/login") //访问路径
    public ResultStatus login(@RequestBody LoginPojo loginPojo){
//        System.out.println(loginPojo.toString());
        ResultStatus resultStatus = new ResultStatus();
        if (loginPojo.getJs_code() == null && loginPojo.getJs_code().length() == 0) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }
        VxUser vxUser= vxUserService.login("wx16428bb434005c77", "9b11d1ddc1ae84b7bc3edfdfdb9a714a",
                loginPojo.getJs_code());

        httpSession.setAttribute("vxUser",vxUser);
        String id = httpSession.getId();
        if (vxUser.getCreatTime() == null) {
            return resultStatus.setCode("700").setMsg(id);
        }
        return resultStatus.setCode("200").setMsg(id).setData(vxUser);
    }


    @PostMapping("/addVxUser")
    public ResultStatus addVxUser(@RequestBody  VxUser vxUser){
        VxUser sessionVxUser = (VxUser) httpSession.getAttribute("vxUser");
        vxUser.setCreatTime(System.currentTimeMillis());
        vxUser.setUpdateTime(System.currentTimeMillis());
        vxUser.setOpenid(sessionVxUser.getOpenid());
        boolean save = vxUserService.save(vxUser);

        if (save){
            httpSession.setAttribute("vxUser",vxUser);
            return ResultStatus.builder().code("200").msg("增加成功").build();
        }else {
            return ResultStatus.builder().code("701").msg("添加失败").build();
        }
    }

    @PostMapping("/updateVxUser")
    public ResultStatus updateVxUser(@RequestBody  VxUser vxUser){
        VxUser sessionVxUser = (VxUser) httpSession.getAttribute("vxUser");
        vxUser.setOpenid(sessionVxUser.getOpenid());
        boolean b = vxUserService.updateById(vxUser);
        if (b) {
            return ResultStatus.builder().code("200").msg("保存成功").build();
        }else {
            return ResultStatus.builder().code("702").msg("保存失败").build();
        }
    }


    @PostMapping("/certification")
    public ResultStatus identifyCertificate(@RequestBody VxUser vxUser) {
        VxUser sessionVxUser = (VxUser) httpSession.getAttribute("vxUser");
        vxUser.setOpenid(sessionVxUser.getOpenid());
        return vxUserService.updateUserInfo(vxUser);
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

    /**
     * 验证用户身份
     * @param vxUser
     * @return
     */



}

