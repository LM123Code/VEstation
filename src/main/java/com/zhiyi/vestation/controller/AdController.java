package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Ad;
import com.zhiyi.vestation.pojo.ResultStatus;
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
    public ResultStatus getFirstPageAd(){
        return adService.getFirstPageList();
    }

    /**
     * 插入或更新广告
     * @param ad 广告对象
     * @return 执行返回的状态
     */
    public ResultStatus insertAd(Ad ad){
        boolean b = adService.saveOrUpdate(ad);
        return b?new ResultStatus().setCode("200").setMsg("ok") :
                new ResultStatus().setMsg("插入失败").setCode("1");
    }

    /**
     * 删除广告
     * @param adId 广告id
     * @return 执行返回的状态
     */
    public ResultStatus delAd(Integer adId){
        boolean b = adService.removeById(adId);
        return b?new ResultStatus().setCode("200").setMsg("ok") :
                new ResultStatus().setMsg("删除失败").setCode("1");
    }
}

