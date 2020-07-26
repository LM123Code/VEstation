package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhiyi.vestation.pojo.Comment;
import com.zhiyi.vestation.mapper.CommentMapper;
import com.zhiyi.vestation.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    /**
     * 根据forum_Id查询该帖子下所有的评论
     * @param forumId
     * @return
     */
    @Override
    public List<Comment> selectByForumId(int forumId) {
        QueryWrapper<Comment> commentWrapper = new QueryWrapper<>();
        commentWrapper.eq("forum_id",forumId);
        List<Comment> comments = baseMapper.selectList(commentWrapper);
        return comments;
    }
}
