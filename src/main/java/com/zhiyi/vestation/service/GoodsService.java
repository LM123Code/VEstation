package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 获取首页商品推荐
     * @return Goods对象列表
     */
    List<Goods> getRecommendGoods();

    /**
     * 获取去某一页的所有Goods
     * @param p 页码
     * @return 商品列表
     */
    List<Goods> getAllGoodsInPage(int p);
}
