package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.Comment;
import com.zhiyi.vestation.pojo.Forum;
import com.zhiyi.vestation.mapper.ForumMapper;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.CommentService;
import com.zhiyi.vestation.service.ForumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.service.StarService;
import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ResultStatus selectForumByCateId(String openid, int forumType, int page) {
        /**
         * 1.先从ES中拿到帖子信息   这里暂时从数据库拿
         */
        QueryWrapper<Forum> wrapper = new QueryWrapper<>();
        wrapper.eq("forum_type",forumType);
        wrapper.orderByDesc("create_date");
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

        ResultStatus resultStatus = new ResultStatus();
        if(openid == null || page <= 0 || forumType <0 || openid.equals("")) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }else if(forums == null || forums.size() == 0) {
            return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setData(forums).setMsg("ok").setCode("200");
    }

    /**
     * 删除帖子
     * @param forumId
     * @return
     */
    @Override
    public ResultStatus deleteForum(int forumId) {
        int delete = baseMapper.deleteById(forumId);
        ResultStatus resultStatus = new ResultStatus();
        if (forumId <= 0) {
            return resultStatus.setMsg("参数异常").setCode("0");
        }else if (delete <= 0) {
            return resultStatus.setCode("1").setMsg("删除失败");
        }
        return resultStatus.setCode("200").setMsg("ok");
    }

    /**
     * 根据forumId查询 发布者的openid用户点赞插入
     * @param forumId
     * @return
     */
    @Override
    public Forum selectOpenid(int forumId) {
        QueryWrapper<Forum> publishOpenidWrapper = new QueryWrapper<>();
        publishOpenidWrapper.select("openid","like_num","comment_unm").eq("forum_id",forumId);
        Forum forum = baseMapper.selectOne(publishOpenidWrapper);
        return forum;
    }

    /**
     * 我的动态
     * @param openid
     * @return
     */
    @Override
    public ResultStatus selectMyForum(String openid, int page) {
        QueryWrapper<Forum> myForumWrapper = new QueryWrapper<>();
        myForumWrapper.eq("openid",openid);
        myForumWrapper.orderByDesc("create_date");
        Page<Forum> myForumPage = new Page<>(page,10);  //分页
        List<Forum> myForums = baseMapper.selectPage(myForumPage, myForumWrapper).getRecords();

        myForums.forEach(forum ->{
            String[] split = forum.getImages().split(",");
            forum.setForumUrls(split);
        });
        System.out.println(myForums);
        //将图片处理成数组

        ResultStatus resultStatus = new ResultStatus();
        if (openid == null || openid.equals("") || page <= 0) {
            return resultStatus.setCode("0").setMsg("参数异常");
        }else if (myForums == null) {
            return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setMsg("ok").setCode("200").setData(myForums);
    }

    /**
     * 查询某用户收藏的帖子
     * @return
     */
    @Override
    public List<Forum> selectCollectForum(int[] collects) {
        ArrayList<Forum> forums = new ArrayList<>(100);
        for(int forumId: collects){
            forums.add(baseMapper.selectById(forumId));
        }
        return forums;
    }

    /**
     * 发布帖子
     * @param forum
     * @return
     */
    @Override
    public int insertForum(Forum forum) {
        return baseMapper.insert(forum);
    }

    /**
     * 根据forumId查询帖子信息
     * @param forumId
     * @return
     */
    @Override
    public Forum selectByForumId(int forumId) {
        return baseMapper.selectById(forumId);
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



}
