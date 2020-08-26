package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.mapper.CommentMapper;
import com.zhiyi.vestation.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.service.ForumService;
import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    VxUserService vxUserService;
    @Autowired
    ForumService forumService;

    /**
     * 根据forum_Id查询该帖子下所有的评论
     * @param forumId
     * @param commentClass
     * @return
     */
    @Override
    public ResultStatus selectByForumId(int forumId, int commentClass) {
        QueryWrapper<Comment> commentWrapper = new QueryWrapper<>();
        commentWrapper.eq("forum_id",forumId);
        commentWrapper.eq("comment_class",commentClass);
        List<Comment> comments = baseMapper.selectList(commentWrapper);  //查询出所有的实体
        List<Comment> firstComments = comments.stream().filter(comment -> {
            return comment.getReplyId().equals("-1");        //查出一级评论
        }).map(comment -> {
            comment.setChildren(findChildren(comment, comments));  //查出二级评论
            return comment;
        }).map(comment -> {
            comment.setVxUser(vxUserService.selectByWrapper(comment.getCommentOpenid()));  //查询出评论者的信息
            return comment;
        }).sorted((comment1, comment2) -> {
            return comment1.getCreateDate().before(comment2.getCreateDate())? -1 : 1;
        }).collect(Collectors.toList());

        ResultStatus resultStatus = new ResultStatus();
        if(forumId < 0) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }else if(firstComments == null || firstComments.size() == 0) {
            return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setData(firstComments).setMsg("ok").setCode("200");

    }

    /**
     * 评论功能
     * @param comment 评论记录
     * @return
     */
    @Override
    public Status commentForum(Comment comment) {

        /**
         * 根据forumId查询出发布者的openid
         */
        Forum forum = forumService.selectOpenid(comment.getForumId());
        comment.setPublishOpenid(forum.getOpenid());
        comment.setLook(false);

        //添加评论量
        forum.setCommentNum(forum.getCommentNum() + 1);
        forumService.updateById(forum);
        /**
         * 评论插入
         */
        int insert = baseMapper.insert(comment);
        return insert > 0? new Status(200,"评论成功") : new Status(0,"评论失败");
    }

    /**
     * 查询评论信息
     * @param publishOpenid
     * @return
     */
    @Override
    public ResultStatus selectCommentMsg(String publishOpenid, int page) {
        /**
         * 查询出未读的评论数量
         */

        QueryWrapper<Comment> commentsMsgCount = new QueryWrapper<>();
        commentsMsgCount.and(commentQueryWrapper -> commentQueryWrapper.eq("publish_openid",publishOpenid).
                eq("look",0));    //条件查询
        commentsMsgCount.orderByDesc("create_date");     //按时间排序
        Integer unreadCount = baseMapper.selectCount(commentsMsgCount);
        /**
         * 查出评论记录
         */
        QueryWrapper<Comment> commentRecords = new QueryWrapper<>();
        commentRecords.eq("publish_openid",publishOpenid);
        commentRecords.orderByDesc("create_date");
        Page<Comment> commentPage = new Page<Comment>(page, 10);
        List<Comment> records = baseMapper.selectPage(commentPage, commentRecords).getRecords();   //查出了评论记录
        CommentMessage commentMessage = new CommentMessage(records, unreadCount);

        ResultStatus resultStatus = new ResultStatus();
        if(publishOpenid == null || page == 0) {
           return resultStatus.setCode("0").setMsg("参数异常");
        }else if(records == null || records.size() == 0) {
           return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setData(commentMessage).setMsg("ok").setCode("200");
    }

    /**
     * 递归查询 查询出每个评论的子评论
     * @param comment
     * @param comments
     * @return
     */
    private List<Comment> findChildren(Comment comment, List<Comment> comments) {
        List<Comment> children = comments.stream().filter(commentItem -> {
            return Integer.parseInt(commentItem.getReplyId().trim()) == comment.getCommetId();
        }).map(commentItem -> {
            commentItem.setChildren(findChildren(commentItem, comments));
            return commentItem;
        }).sorted((comment1,comment2) ->{
            return comment1.getCreateDate().before(comment2.getCreateDate())? -1 : 1;
        }).collect(Collectors.toList());
        return children;
    }

    /**
     * 查询二级评论
     * @param relyId
     * @return
     */
    @Override
    public List<Comment> selectSecondComment(int relyId) {
        QueryWrapper<Comment> scWrapper = new QueryWrapper<>();
        scWrapper.eq("rely_id", relyId);
        List<Comment> secondComments = baseMapper.selectList(scWrapper);
        return secondComments;
    }
}
