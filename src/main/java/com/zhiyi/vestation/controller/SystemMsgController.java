package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.SystemMsg;
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
@RequestMapping("${api-url}/system-msg")
public class SystemMsgController {

    @GetMapping("/selectMessage")
    public List<SystemMsg> selectMessage() {
      return null;
    }
}

