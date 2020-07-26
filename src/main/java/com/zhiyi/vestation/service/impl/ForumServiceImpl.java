package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.Comment;
import com.zhiyi.vestation.pojo.Forum;
import com.zhiyi.vestation.mapper.ForumMapper;
import com.zhiyi.vestation.pojo.Goods;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.CommentService;
import com.zhiyi.vestation.service.ForumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.sql.Wrapper;
import java.util.HashMap;
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

    /**
     * 通过fourm_type查询帖子
     * @param forum_type
     * @return
     */
    @Override
    public List<Forum> selectForumByCateId(int forum_type,int page) {
        /**
         * 1.先从ES中拿到帖子信息   这里暂时从数据库拿
         */
        QueryWrapper<Forum> wrapper = new QueryWrapper<Forum>();
        wrapper.eq("forum_type",forum_type);
        wrapper.orderByAsc("create_date");
        Page<Forum> forumPage = new Page<Forum>(page,10);
        List<Forum> forums = baseMapper.selectPage(forumPage, wrapper).getRecords();
        /**
         * 2.使用Es中拿到的某些属性列在mysql中查询出作者信息
         */
        addVxUser(forums);
        /**
         * 3.查出帖子的评论
         */
        //addComments(forums);
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
