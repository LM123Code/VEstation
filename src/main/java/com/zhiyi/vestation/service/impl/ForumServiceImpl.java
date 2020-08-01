package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.Comment;
import com.zhiyi.vestation.pojo.Forum;
import com.zhiyi.vestation.mapper.ForumMapper;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.CommentService;
import com.zhiyi.vestation.service.ForumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.service.StarService;
import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements ForumService {
    @Autowired
    VxUserService vxUserService;
    @Autowired
    CommentService commentService;
    @Autowired
    StarService starService;

    /**
     * 通过fourm_type查询帖子
     * @param forumType
     * @return
     */
    @Override
    public List<Forum> selectForumByCateId(String openid, int forumType,int page) {
        /**
         * 1.先从ES中拿到帖子信息   这里暂时从数据库拿
         */
        QueryWrapper<Forum> wrapper = new QueryWrapper<>();
        wrapper.eq("forum_type",forumType);
        wrapper.orderByAsc("create_date");
        Page<Forum> forumPage = new Page<Forum>(page,10);
        List<Forum> forums = baseMapper.selectPage(forumPage, wrapper).getRecords();
        /**
         * 2.使用Es中拿到的某些属性列在mysql中查询出作者信息
         */
        forums = addVxUser(forums);
        /**
         * 3.查询出该用户是否  收藏这些帖子
         */
        forums = selectCollectStatus(openid, forums, forumType);
        /**
         * 4.查询出该用户是否  点赞过这些帖子
         */
        forums = selectLikeStatus(openid, forums);
        return forums;
    }

    /**
     * 删除帖子
     * @param forumId
     * @return
     */
    @Override
    public int deleteForum(int forumId) {
       return baseMapper.deleteById(forumId);
    }

    /**
     * 根据forumId查询 发布者的openid用户点赞插入
     * @param forumId
     * @return
     */
    @Override
    public String selectOpenid(int forumId) {
        QueryWrapper<Forum> publishOpenidWrapper = new QueryWrapper<>();
        publishOpenidWrapper.select("openid").eq("forum_id",forumId);
        Forum forum = baseMapper.selectOne(publishOpenidWrapper);
        return forum.getOpenid();
    }

    /**
     *工具类  每个帖子添加作者信息
     * @param list 帖子列表
     * @return
     */
    public List<Forum> addVxUser(List<Forum> list) {
        HashMap<String, VxUser> map = new HashMap<String, VxUser>(); //存储需要的vxUser
        for (Forum forum:list) {
            String openid = forum.getOpenid(); //获取当前商品的发布者openid
            VxUser vxUser = map.get(openid); //从map中获取vxUser
            if(vxUser == null){ //不存在就进行查询
                vxUser = vxUserService.selectByWrapper(openid);
                map.put(openid, vxUser); //加入map
            }
            forum.setVxUser(vxUser); //为每个商品设置发布者信息
        }
        return list; //返回帖子列表
    }

    /**
     * 查询帖子是否被用户收藏过
     * @param openid
     * @param list
     * @param forumType
     * @return
     */
    private List<Forum> selectCollectStatus(String openid, List<Forum> list, int forumType) {
        String collect = vxUserService.selectCollect(openid, forumType);  //根据openid和forumType查询出了收藏的历史
        String[] split = collect.split(",");
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(split));
        for (Forum forum: list) {
            if(hashSet.contains(""+forum)) {
                forum.setCollectStatus(true);
            }else {
                forum.setCollectStatus(false);
            }
        }
        return list;
    }

    /**
     * 查询帖子是否被用户点赞过
     * @param openid
     * @param forums
     */
    private List<Forum> selectLikeStatus(String openid, List<Forum> forums) {
        for (Forum forum: forums) {
            forum.setLikeStatus(starService.selectLikeStatusOfUser(openid,forum.getForumId()));
        }
        return forums;
    }

    /**
     * 查找帖子评论
     */
    public List<Forum> addComments(List<Forum> list) {
        for (Forum forum:list) {
            QueryWrapper<Object> commentWrapper = new QueryWrapper<>();
            List<Comment> comments = commentService.selectByForumId(forum.getForumId());
            forum.setComments(comments);
        }
        return list; //返回帖子列表
    }
}
