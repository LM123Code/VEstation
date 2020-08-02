package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyi.vestation.pojo.ResultStatus;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public interface AdService extends IService<Ad> {

    /**
     * 获取首页广告
     * @return
     */
    ResultStatus getFirstPageList();
}
