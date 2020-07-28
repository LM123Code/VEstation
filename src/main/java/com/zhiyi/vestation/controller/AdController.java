package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Ad;
import com.zhiyi.vestation.pojo.Status;
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

    /**
     * 插入或更新广告
     * @param ad 广告对象
     * @return 执行返回的状态
     */
    public Status insertAd(Ad ad){
        boolean b = adService.saveOrUpdate(ad);
        return b?new Status(200, "成功"):new Status(0, "失败");
    }

    /**
     * 删除广告
     * @param adId 广告id
     * @return 执行返回的状态
     */
    public Status delAd(Integer adId){
        boolean b = adService.removeById(adId);
        return b?new Status(200, "成功"):new Status(0, "失败");
    }
}

