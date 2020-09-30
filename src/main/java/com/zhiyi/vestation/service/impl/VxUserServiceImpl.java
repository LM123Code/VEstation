package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.mapper.VxUserMapper;

import com.zhiyi.vestation.service.ForumService;
import com.zhiyi.vestation.service.VxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.utils.AuthUtil;
import com.zhiyi.vestation.utils.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.*;

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
    @Autowired
    ForumService forumService;

    @Autowired
    AuthUtil authUtil;

    /**
     * 登录方法
     *
     * @param appid 小程序唯一标识
     * @param secret
     * @param js_code 登陆凭证
     * @return
     */

    @Override
    public VxUser login(String appid, String secret, String js_code) {
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
        System.out.println("=====0"+openid);
        VxUser vxUser = vxUserMapper.selectById(openid);

        if(vxUser == null){
            vxUser = new VxUser();
            //新用户
            vxUser.setOpenid(openid);
            return vxUser;
        }else {
            return vxUser;
        }
    }

    /**
     * 更新用户信息
     * @param vxUser
     * @return
     */
    @Override
    public ResultStatus updateUserInfo(VxUser vxUser) {
        boolean isAuth = false;
        //这里要判断一下是学生认证还是老师认证
        if (vxUser.getCompanyName() != null){
            boolean b = authUtil.authIdentify(vxUser.getRealName(), vxUser.getCompanyName(), vxUser.getProfession(), vxUser.getCompanyUrl());
            if (b) {
                vxUser.setCompanyExit(1);
                isAuth = true;
            }else {
                vxUser.setCompanyExit(2);
            }
            //职场认证
        }else if(vxUser.getSchoolName() != null){

            //校园认证
            boolean b = authUtil.authIdentify(vxUser.getRealName(), vxUser.getSchoolName(), vxUser.getSchoolMajor(), vxUser.getSchoolUrl());
            if (b) {
                vxUser.setSchoolExit(1);
                isAuth = true;
            }else {
                vxUser.setSchoolExit(2);
            }
        }

        UpdateWrapper<VxUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("openid",vxUser.getOpenid());
        int update = baseMapper.update(vxUser, updateWrapper);
        if (!isAuth) {
            return ResultStatus.builder().code("705").msg("认证失败").build();
        }
        ResultStatus resultStatus = new ResultStatus();
        if (vxUser == null || vxUser.getOpenid() == null) {
            return resultStatus.setCode("800").setMsg("参数异常");
        }else if (update <= 0) {
            return resultStatus.setMsg("更新失败").setCode("801");
        }
        return resultStatus.setCode("200").setMsg("ok");
    }

    /**
     * 获取vxUser的指定属性
     * @return vxUser对象
     */
    @Override
    public VxUser selectByWrapper(@Param("openid") String openid) {
        QueryWrapper<VxUser> wrapper = new QueryWrapper<>();
        wrapper.select("openid","nick_name", "sex","name","user_avatar_url", "school_name","school_url",
                "school_exit","profession","name", "phone_number", "wechat","company_name","company_url",
                "company_exit","address").eq("openid",openid);
        return baseMapper.selectOne(wrapper);

    }

    /**
     *收藏帖子
     * @param openid   用户标识
     * @param forumId  帖子标识
     * @param forumType  帖子类型
     * @return
     */
    @Override
    public ResultStatus collectForum(String openid, int forumId, int forumType) {
       boolean bool = false; //收藏标志  收藏成功之后会将其改为true
       int update = -1;
       VxUser vxUser = null;

        if(forumType == 1) {
            vxUser = selectUserUtil(openid, "goods_ids");
            String goodsIds = vxUser.getGoodsIds();
            if(goodsIds.equals("")) {
                vxUser.setGoodsIds(""+forumId);
            }else {
                vxUser.setGoodsIds(goodsIds + "," + forumId);
            }
        }else if(forumType == 2) {
            vxUser = selectUserUtil(openid, "room_ids");
            String roomIds = vxUser.getRoomIds();
            if(roomIds == null) {
                vxUser.setGoodsIds("" + forumId);
            }else {
                vxUser.setRoomIds(roomIds + "," + forumId);  //,2,3,6
            }
        }else {
            vxUser = selectUserUtil(openid, "job_ids");
            String jobIds = vxUser.getJobIds();
            if(jobIds == null) {
                vxUser.setJobIds(""+forumId);
            }else {
                vxUser.setGoodsIds(vxUser.getJobIds() + "," + forumId);
            }
        }
        UpdateWrapper<VxUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("openid", openid);
        update = baseMapper.update(vxUser, updateWrapper);

        ResultStatus resultStatus = new ResultStatus();
        if(openid == null || openid.equals("") || forumId < 0 || forumType < 0) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }else if(update <= 0) {
            return resultStatus.setCode("1").setMsg("点赞失败");
        }
        return resultStatus.setMsg("ok").setCode("200");
    }

    /**
     * 查询某个用户的收藏帖子，用户我的动态页面展示
     * @param openid
     * @param forumType
     * @return
     */
    @Override
    public List selectCollectsOfUser(String openid, int forumType) {
        String collectString= selectCollect(openid, forumType); //根据收藏的类型查出收藏帖子id的字符串
        String[] collects = collectString.split(",");
        if(collects.length == 1 && collects[0].equals("")) {
            return null;
        }
        int[] array = Arrays.asList(collects).stream().mapToInt(Integer::parseInt).toArray();  //将收藏id字符串转为int数组
        List<Forum> forums = forumService.selectCollectForum(array);
        return forums;
    }

    /**
     * 根据openid 和forumType查询出某个用户收藏的某种帖子  用于查询用户的对帖子的收藏状态
     * @param openid
     * @param forumType
     * @return
     */
    @Override
    public String selectCollect(String openid, int forumType) {
        VxUser vxUser = null;
        if(forumType == 1) {
           vxUser = selectLikeStatusUtil(openid, "goods_ids");
           return vxUser.getGoodsIds() == null? "" :vxUser.getGoodsIds();
        }else if(forumType == 2) {
            vxUser = selectLikeStatusUtil(openid, "room_ids");
            return vxUser.getRoomIds() == null? "" :vxUser.getRoomIds();
        }else {
            vxUser = selectLikeStatusUtil(openid, "job_ids");
            return vxUser.getJobIds() == null? "" :vxUser.getJobIds();
        }
    }

    /**
     * 根据openid查询用户的所有信息
     * @param openid
     * @return
     */
    @Override
    public ResultStatus selectUserInfo(String openid) {
        QueryWrapper<VxUser> userInfoWrapper = new QueryWrapper<>();
        userInfoWrapper.eq("openid",openid);
        VxUser vxUser = baseMapper.selectOne(userInfoWrapper);
        ResultStatus resultStatus = new ResultStatus();
        if(openid == null || openid.equals("")) {
           return resultStatus.setCode("0").setMsg("参数异常");
        }else if(vxUser == null) {
           return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setMsg("ok").setCode("200").setData(vxUser);

    }



    /**
     * 查询用户是否学生身份认证
     * @param openid
     * @return
     */
    @Override
    public Map<String, String> selectStudentCertification(String openid) {
        QueryWrapper<VxUser> vxUserQueryWrapper = new QueryWrapper<>();
        vxUserQueryWrapper.select("openid","school_exit").eq("openid",openid);
        VxUser vxUser = baseMapper.selectOne(vxUserQueryWrapper);
        return certificationStatus(vxUser.getSchoolExit());
    }

    /**
     * 查询用户是否职工身份认证
     * @param openid
     * @return
     */
    @Override
    public Map<String, String> selectStaffCertification(String openid) {
        QueryWrapper<VxUser> vxUserQueryWrapper = new QueryWrapper<>();
        vxUserQueryWrapper.select("openid","company_exit").eq("openid",openid);
        VxUser vxUser = baseMapper.selectOne(vxUserQueryWrapper);
        return certificationStatus(vxUser.getCompanyExit());
    }

    @Override
    public List<VxUser> getAllUser() {
        VxUser vxUser = new VxUser();
        vxUser.setExist(true);
        QueryWrapper<VxUser> vxUserQueryWrapper = new QueryWrapper<>();
        vxUserQueryWrapper.eq("exist",1);
        List<VxUser> users = vxUserMapper.selectList(vxUserQueryWrapper);
        return users;
    }

    /**
     * 根据状态码判断身份认证的阶段
     * @param certificationCode
     * @return
     */
    @Nullable
    private Map<String, String> certificationStatus(int certificationCode) {
        HashMap<String, String> certificationStatus = new HashMap<String,String>();
        if (certificationCode == 0) {
            certificationStatus.put("code", Integer.toString(certificationCode));
            certificationStatus.put("status", "未认证");
        }else if (certificationCode == 1) {
            certificationStatus.put("code", Integer.toString(certificationCode));
            certificationStatus.put("status", "已认证");
        }else if (certificationCode == 2) {
            certificationStatus.put("code", Integer.toString(certificationCode));
            certificationStatus.put("status", "未审核");
        }else if(certificationCode == 3) {
            certificationStatus.put("code", Integer.toString(certificationCode));
            certificationStatus.put("status", "审核中");
        }else if (certificationCode == 4) {
            certificationStatus.put("code", Integer.toString(certificationCode));
            certificationStatus.put("status", "审核未通过");
        }
        return certificationStatus;
    }


    private VxUser selectLikeStatusUtil(String openid, String attrName) {
        QueryWrapper<VxUser> collectWrapper = new QueryWrapper<>();
        collectWrapper.select("openid", attrName).eq("openid", openid);
        return baseMapper.selectOne(collectWrapper);
    }
    /**
     * 收藏时的查询工具，根据不同的attrName查询不同种类的收藏
     * @param openid
     * @param attrName
     * @return
     */
    private VxUser selectUserUtil(String openid, String attrName) {
        QueryWrapper<VxUser> userWrapper = new QueryWrapper<>();
        userWrapper.select("openid", attrName).eq("openid",openid);
        VxUser vxUser = baseMapper.selectOne(userWrapper);
        return vxUser;
    }


}
