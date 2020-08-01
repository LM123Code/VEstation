package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Forum;
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
public interface ForumService extends IService<Forum> {
    /**
     * 根据分类id查询帖子
     */
    public List<Forum> selectForumByCateId(String openid, int forumType,int page);

    /**
     * 通过forumId删除帖子
     * @param forumId
     * @return
     */
    public int deleteForum(int forumId);

    /**
     * 根据forumId查询 发布者openid 用于点赞时插入数据
     * @param forumId
     * @return
     */
    public String selectOpenid(int forumId);
}
