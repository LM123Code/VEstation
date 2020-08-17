package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Forum;
import com.zhiyi.vestation.pojo.Goods;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.ForumService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@RestController
@RequestMapping("${api-url}/forum")
public class ForumController {
    @Autowired
    ForumService forumService;     //service的注入

    /**
     * 通过forum_type查询帖子使用在动态页
     * @param forumType  帖子分类
     * @param openid 用户id 用来查询该用户是否点赞过这些帖子
     * @param  page  分页页码
     * @return
     */
    @GetMapping("/dongtai")
    public ResultStatus selectForumByCateId(String openid, int forumType, int page) {
       return forumService.selectForumByCateId(openid, forumType, page);
    }

    /**
     * 我的动态
     * @param openid
     * @return
     */
    @GetMapping("/myForum")
    public ResultStatus selectMyForum(String openid, int page) {
        return forumService.selectMyForum(openid, page);
    }

    /**
     * 发布帖子
     * @param forum
     * @return
     */
    @RequestMapping("/publishForum")
    public ResultStatus publishForum(Forum forum) {
        int insert = forumService.insertForum(forum);
        ResultStatus resultStatus = new ResultStatus();
        if (forum == null) {
            return resultStatus.setMsg("参数异常").setCode("0");
        }else if(insert <= 0) {
            return resultStatus.setCode("1").setMsg("发布失败，请检查参数");
        }
        return resultStatus.setMsg("ok").setCode("200");
    }

    /**
     * 编辑我的动态
     * @param forum
     * @return
     */
    @RequestMapping("/editForum")
    public ResultStatus editForum(Forum forum) {
        boolean update = forumService.updateById(forum);
        if(forum == null || forum.getForumId() <= 0) {
            return ResultStatus.builder().code("0").msg("参数异常").build();
        }else if(update == false) {
            return ResultStatus.builder().code("1").msg("编辑失败").build();
        }else {
            return ResultStatus.builder().code("200").msg("ok").build();
        }
    }

    /**
     * 删除我的动态
     * @param forumId
     * @return
     */
    @GetMapping("/deleteForum")
    public ResultStatus deleteForum(int forumId) {
       return forumService.deleteForum(forumId);
    }

    /**
     * 传来关键字查询内容
     * @param key 关键字
     * @return
     */
    @PostMapping("/selectForumListAboutKeyWorlds")
    public ResultStatus selectForumListAboutKeyWorlds(String key){
        List<Forum> list = forumService.selectForumListAboutKeyWorlds(key);
        return new ResultStatus().setMsg("200").setMsg("查询成功").setData(list);
    }
}

