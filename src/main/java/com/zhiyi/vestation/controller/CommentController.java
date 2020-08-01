package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Comment;
import com.zhiyi.vestation.pojo.CommentMessage;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @RequestMapping("/selectComments")
    public List<Comment> selectFirstComment(int forumId) {
       return commentService.selectByForumId(forumId);
    }

    /**
     * 评论功能
     * @param comment
     * @return
     */
    @RequestMapping("/commentForum")
    public Status commentForum(Comment comment) {
        return commentService.commentForum(comment);
    }

    /**
     * 查询评论信息
     * @param publishOpenid
     * @param page
     * @return
     */
    @GetMapping("/commentMsg")
    public ResultStatus<CommentMessage> selectCommentMessage(String publishOpenid, int page) {
        return commentService.selectCommentMsg(publishOpenid, page);
    }


    /**
     * 查询二级评论
     * @param replyId 被评论的 “评论”id
     * @return
     */
    @GetMapping("/secondComments")
    public List<Comment> selectSecondComment(int replyId) {
        return commentService.selectSecondComment(replyId);
    }
}

