package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhiyi.vestation.pojo.SystemMsg;
import com.zhiyi.vestation.mapper.SystemMsgMapper;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.SystemMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class SystemMsgServiceImpl extends ServiceImpl<SystemMsgMapper, SystemMsg> implements SystemMsgService {

    @Autowired
    SystemMsgMapper systemMsgMapper;
    @Autowired
    VxUserService vxUserService;

    @Override
    public boolean addSystemMsg(String msg) throws ParseException {
        SystemMsg systemMsg = new SystemMsg();
        systemMsg.setCreateDate(new Date());
        systemMsg.setExist(true);
        systemMsg.setLook(false);
        systemMsg.setMsgContent(msg);
        List<VxUser> allUser = vxUserService.getAllUser();
        for (VxUser vxUser: allUser) {
            systemMsg.setToOpenid(vxUser.getOpenid());
            int insert = systemMsgMapper.insert(systemMsg);
            if (insert != 1)
                return false;
        }
        return true;
    }

    @Override
    public List<SystemMsg> getSystemMsgByOpenId(String openId) {
        QueryWrapper<SystemMsg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("to_openid",openId).orderByAsc("create_date");
        List<SystemMsg> systemMsgList = systemMsgMapper.selectList(queryWrapper);
        return systemMsgList;
    }

    @Override
    public boolean setSystemMsgLookToTrue(String openId, String msgId) {
        QueryWrapper<SystemMsg> queryWrapper = new QueryWrapper<>();
        //先查询出来
        queryWrapper.eq("to_openid",openId).eq("system_msg_id",msgId);
        SystemMsg systemMsg = systemMsgMapper.selectOne(queryWrapper);
        //看看是不是有这个消息，没有就报错
        if (systemMsg == null)
            return false;
        systemMsg.setLook(true);
        //修改
        queryWrapper.eq("to_openid",openId).eq("system_msg_id",msgId);
        int update = systemMsgMapper.update(systemMsg, queryWrapper);
        return update==1;
    }
}
