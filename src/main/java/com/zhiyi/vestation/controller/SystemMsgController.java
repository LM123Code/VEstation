package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.SystemMsg;
import com.zhiyi.vestation.service.SystemMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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
@RequestMapping("${api-url}/system-msg")
public class SystemMsgController {

    @Autowired
    SystemMsgService systemMsgService;

    @GetMapping("/selectMessage")
    public List<SystemMsg> selectMessage() {
      return null;
    }

    /**
     * 添加系统消息
     * @param msg
     * @return
     */
    @PostMapping("/setSysMsg")
    public ResultStatus setSysMsg(String msg){
        boolean b = false;
        try {
            b = systemMsgService.addSystemMsg(msg);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResultStatus().setCode("400").setMsg("添加失败");
        }
        if (b){
            return new ResultStatus().setCode("200").setMsg("添加成功");
        }
        return new ResultStatus().setCode("400").setMsg("添加失败");
    }

    /**
     * 获取某一个用户的系统消息
     */
    @RequestMapping("/getSystemMsgByOpenId")
    public ResultStatus getSystemMsgByOpenId(String openId){
        List<SystemMsg> systemMsgList = systemMsgService.getSystemMsgByOpenId(openId);
        return new ResultStatus().setCode("200").setMsg("查询成功").setData(systemMsgList);
    }

    /**
     * 设置某个用户的某个系统消息为看过
     */
    @RequestMapping("/setSystemMsgLookToTrue")
    public ResultStatus setSystemMsgLookToTrue(String openId,String msgId){
        boolean b = systemMsgService.setSystemMsgLookToTrue(openId, msgId);
        if (b){
            return new ResultStatus().setCode("200").setMsg("设置成功");
        }
        return new ResultStatus().setCode("400").setMsg("设置失败");
    }
}

