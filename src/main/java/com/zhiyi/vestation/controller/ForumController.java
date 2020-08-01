package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Forum;
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
    public List<Forum> selectForumByCateId(String openid, int forumType, int page) {
        List<Forum> forums = forumService.selectForumByCateId(openid, forumType, page);
        return forums;
    }
    @GetMapping("/delete")
    public int deleteForum(int forumId) {
       return forumService.deleteForum(forumId);
    }
}

