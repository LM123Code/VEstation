package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.LikeMessage;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Star;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyi.vestation.pojo.Status;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public interface StarService extends IService<Star> {
    /**
     * 查询某用户的被点攒的历史
     * @param openid
     * @return
     */
    public Boolean selectLikeStatusOfUser(String openid, int forumId);

    /**
     * 点赞功能
     * @param star
     * @return
     */
    public Status likeForum(Star star);

    /**
     * 查询点赞信息
     * @param publishOpenid
     * @return
     */
    public ResultStatus<LikeMessage> selectLikeMessage(int page, String publishOpenid);
}
