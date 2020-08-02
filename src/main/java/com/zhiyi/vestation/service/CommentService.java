package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyi.vestation.pojo.CommentMessage;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public interface CommentService extends IService<Comment> {
    /**
     * 根据forum_id查询该帖子下的所有评论
     * @param forumId
     * @return
     */
    public ResultStatus selectByForumId(int forumId);

    /**
     * 评论功能
     * @param comment 评论记录
     * @return
     */
    public Status commentForum(Comment comment);

    /**
     * 根据用户的
     * @param publishOpenid
     * @param page 分页
     * @return
     */
    public ResultStatus selectCommentMsg(String publishOpenid, int page);


    /**
     * 二级评论查询
     * @param relyId
     * @return
     */
    public List<Comment> selectSecondComment(int relyId);
}
