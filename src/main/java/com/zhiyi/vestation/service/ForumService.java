package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Forum;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyi.vestation.pojo.ResultStatus;
import org.apache.velocity.runtime.directive.contrib.For;

import javax.xml.transform.Result;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public interface ForumService extends IService<Forum> {
    /**
     * 根据分类id查询帖子
     */
    public ResultStatus selectForumByCateId(String openid, int forumType, int page);

    /**
     * 通过forumId删除帖子
     * @param forumId
     * @return
     */
    public ResultStatus deleteForum(int forumId);

    /**
     * 根据forumId查询 发布者openid 用于点赞时插入数据
     * @param forumId
     * @return
     */
    public Forum selectOpenid(int forumId);

    /**
     * 我的动态
     * @param openid
     * @return
     */
    public ResultStatus selectMyForum(String openid, int page);

    /**
     * 根据收藏的帖子id查询所有的被收藏帖子的信息
     * @return
     */
    public List<Forum> selectCollectForum(int[] collects);

    /**
     * 发布帖子
     * @param forum
     * @return
     */
    public int insertForum(Forum forum);


    /**
     * 根据forumID
     * @return
     */
    public Forum selectByForumId(int forumId);
}
