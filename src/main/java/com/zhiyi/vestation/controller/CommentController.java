package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Comment;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @Autowired
    HttpSession httpSession;

    /**
     * 根据forum查询贴的一级评论
     * @param forumId 帖子id
     * @param commentClass 对应的类别
     * @return
     */
    @RequestMapping("/selectComments")
    public ResultStatus selectFirstComment(int forumId,int commentClass) {
       return commentService.selectByForumId(forumId, commentClass);
    }

    /**
     * 评论功能
     * @param comment
     * @return
     */
    @RequestMapping("/addComment")
    public ResultStatus commentForum(@RequestBody  Comment comment) {
        VxUser sessionVxUser = (VxUser) httpSession.getAttribute("vxUser");
        comment.setPublishOpenid(sessionVxUser.getOpenid());
        comment.setCreateDate(new Date());
        comment.setLook(false);
//        return commentService.commentForum(comment);
        boolean save = commentService.save(comment);
        if (save) {
            return ResultStatus.builder().code("200").msg("添加论成功").build();
        }
        return ResultStatus.builder().code("705").msg("添加评论失败").build();
    }


    /**
     * 查询评论信息
     * @param publishOpenid
     * @param page
     * @return
     */
    @GetMapping("/commentMsg")
    public ResultStatus selectCommentMessage(String publishOpenid, int page) {
        return commentService.selectCommentMsg(publishOpenid, page);
    }



}

