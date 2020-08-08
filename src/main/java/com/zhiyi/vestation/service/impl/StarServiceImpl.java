package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.mapper.LikeMapper;
import com.zhiyi.vestation.service.ForumService;
import com.zhiyi.vestation.service.StarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StarServiceImpl extends ServiceImpl<LikeMapper, Star> implements StarService {
    @Autowired
    ForumService forumService;

    /**
     * 查询某用户是否点赞过某文章
     * @param openid
     * @param forumId
     * @return
     */
    @Override
    public Boolean selectLikeStatusOfUser(String openid, int forumId) {
        QueryWrapper<Star> starWrapper = new QueryWrapper<>();
        starWrapper.and(wrapper -> wrapper.eq("openid", openid).eq("forum_id",forumId));
        Star star = baseMapper.selectOne(starWrapper);
        return star != null? true: false ;
    }

    /**
     * 点赞
     * @param star
     * @return
     */
    @Override
    public ResultStatus likeForum(Star star) {
        /**
         * 1.查询出被点人文章人的发布者
         */
        Forum forum = forumService.selectOpenid(star.getForumId());
        /**
         * 2.点赞
         */
        star.setPublishOpenid(forum.getOpenid());
        star.setLook(false);
        forum.setLikeNum(forum.getLikeNum() + 1);  //增加点赞量
        forumService.updateById(forum);
        /**
         * 3.插入记录
         */
        int insert = baseMapper.insert(star);

        ResultStatus resultStatus = new ResultStatus();
        if(star.getOpenid() == null || star.getForumId() < 0) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }else if(insert <= 0) {
            return resultStatus.setCode("1").setMsg("点赞失败");
        }
        return resultStatus.setMsg("ok").setCode("200");
    }

    /**
     * 查询点赞信息(未读的点赞数量)
     * @param publishOpenid
     * @return
     */
    @Override
    public ResultStatus selectLikeMessage(int page, String publishOpenid) {
        /**
         * 查询未读的点赞记录数
         */
        QueryWrapper<Star> countWrapper = new QueryWrapper<>();
        countWrapper.and(starQueryWrapper -> starQueryWrapper.eq("publish_openid",publishOpenid).
                eq("look",0));      //and条件查询
        Integer unreadCount = baseMapper.selectCount(countWrapper);
        /**
         * 查出点赞记录
         */
        Page<Star> likePage = new Page<>(page,10);
        QueryWrapper<Star> likeMessageWrapper = new QueryWrapper<>();
        likeMessageWrapper.eq("publish_openid",publishOpenid);
        likeMessageWrapper.orderByDesc("create_date");
        List<Star> likes = baseMapper.selectPage(likePage, likeMessageWrapper).getRecords();
        LikeMessage likeMessage = new LikeMessage(likes, unreadCount);

        ResultStatus resultStatus = new ResultStatus();
        if(publishOpenid == null || page == 0) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }else if(likes == null || likes.size() == 0) {
            return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setData(likeMessage).setMsg("ok").setCode("200");
    }
}
