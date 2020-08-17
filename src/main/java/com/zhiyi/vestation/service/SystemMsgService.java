package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.SystemMsg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public interface SystemMsgService extends IService<SystemMsg> {

    /**
     * 添加系统信息
     * @param systemMsg
     * @return
     */
    public boolean addSystemMsg(String systemMsg) throws ParseException;

    /**
     * 通过openid获取该用户用没有消息未读
     * @param openId
     * @return
     */
    public List<SystemMsg> getSystemMsgByOpenId(String openId);

    /**
     * 设置某个用户的某一条msg为已读状态
     * @param openId 用户的唯一标识
     * @param msgId msg的唯一标识
     * @return
     */
    boolean setSystemMsgLookToTrue(String openId,String msgId);
}
