package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.mapper.VxUserMapper;
import com.zhiyi.vestation.service.VxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.utils.HttpRequest;
import org.apache.ibatis.annotations.Param;
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
        int status = baseMapper.insertOrUpdateByOpenid(openid, nickName, userAvatarUrl);


        map.put("status", status); //登录成功status为1，失败为0
        map.put("openid",openid);

        return map;
    }

    /**
     * 获取vxUser的指定属性
     * @return vxUser对象
     */
    @Override
    public VxUser selectByWrapper(@Param("openid") String openid) {
        QueryWrapper<VxUser> wrapper = new QueryWrapper<>();
        wrapper.select("nick_name", "user_avatar_url", "company_exit", "school_exit").eq("openid",openid);
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
     * 根据openid和 forumType查询某个人收藏的某种帖子
     * @param openid
     * @param forumType
     * @return
     */
    @Override
    public String selectCollect(String openid, int forumType) {
        VxUser vxUser = null;
        if(forumType == 1) {
           vxUser = selectLikeStatusUtil(openid, "goods_ids");
           return vxUser.getGoodsIds() == null ?"" :vxUser.getGoodsIds();
        }else if(forumType == 2) {
            vxUser = selectLikeStatusUtil(openid, "room_ids");
            return vxUser.getRoomIds() == null ?"" :vxUser.getRoomIds();
        }else {
            vxUser = selectLikeStatusUtil(openid, "job_ids");
            return vxUser.getJobIds() == null ?"" :vxUser.getJobIds();
        }
    }

    private VxUser selectLikeStatusUtil(String openid, String attrName) {
        QueryWrapper<VxUser> collectWrapper = new QueryWrapper<>();
        collectWrapper.select("openid", attrName).eq("openid", openid);
        return baseMapper.selectOne(collectWrapper);
    }
    /**
     * 收藏时的查询工具
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
