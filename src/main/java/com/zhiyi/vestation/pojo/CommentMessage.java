package com.zhiyi.vestation.pojo;

import lombok.Data;

import java.util.List;
@Data
public class CommentMessage {
    /**
     * 未读记录统计
     */
    private int unreadCount;

    /**
     * 评论记录
     */
    private List<Comment> comments;


    public CommentMessage(List<Comment> comments, int unreadCount) {
        this.setComments(comments);
        this.setUnreadCount(unreadCount);
    }
}
