package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Forum;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.ForumService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
     * 删除我的动态
     * @param forumId
     * @return
     */
    @GetMapping("/deleteForum")
    public ResultStatus deleteForum(int forumId) {
       return forumService.deleteForum(forumId);
    }
}

