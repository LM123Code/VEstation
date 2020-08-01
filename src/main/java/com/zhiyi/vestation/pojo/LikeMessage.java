package com.zhiyi.vestation.pojo;

import lombok.Data;

import java.util.List;
@Data
public class LikeMessage {
    /**
     * 未读的记录数量
     */
    private int unreadCount;

    /**
     * 点赞记录
     */
    private List<Star> likes;


    public LikeMessage(List likes, int unreadCount) {
        this.setLikes(likes);
        this.setUnreadCount(unreadCount);
    }
}
