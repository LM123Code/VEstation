package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

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
    public List<Comment> selectByForumId(int forumId);
}
