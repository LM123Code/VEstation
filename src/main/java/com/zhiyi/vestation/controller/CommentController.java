package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Comment;
import com.zhiyi.vestation.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("${api-url}/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 根据forum查询贴的一级评论
     * @param forumId
     * @return
     */
    @RequestMapping("/firstComments")
    public List<Comment> selectByForumId(int forumId) {
       return commentService.selectByForumId(forumId);
    }
}

