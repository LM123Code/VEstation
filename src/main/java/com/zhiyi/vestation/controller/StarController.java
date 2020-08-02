package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.LikeMessage;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Star;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@RestController
@RequestMapping("${api-url}/like")
public class StarController {
    @Autowired
    StarService starService;

    /**
     * 根据opnid和forumId查询该用户是否点赞过个该文章
     * @param openid
     * @param forumId
     * @return
     */
    @RequestMapping("/selectLike")
    public Boolean selectForumLikes(String openid, int forumId) {
        return starService.selectLikeStatusOfUser(openid,forumId);
    }

    /**
     * 点赞功能
     * @param star
     * @return
     */
    @RequestMapping("/clickLike")
    public ResultStatus likeForum(Star star) {
       return starService.likeForum(star);
    }

    /**
     * 查询点赞消息
     * @param publishOpenid
     * @return
     */
    @GetMapping("/likeMsg")
    public ResultStatus selectLikeMessage(String publishOpenid, int page) {
       return starService.selectLikeMessage(page,publishOpenid);
    }
}

