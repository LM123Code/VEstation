package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Ad;
import com.zhiyi.vestation.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("${api-url}/ad")
public class AdController {

    @Autowired
    AdService adService;  //注入广告的service对象

    /**
     * 获取首页广告列表
     * @return
     */
    @ResponseBody
    @GetMapping("/first-page")
    public List<Ad> getFirstPageAd(){
        return adService.getFirstPageList();
    }
}

