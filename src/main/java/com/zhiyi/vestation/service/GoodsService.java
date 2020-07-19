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

    /**
     * 为集合中的商品添加发布者的属性
     * @param list 商品集合
     * @return 较完整的商品集合
     */
    List<Goods> addVxUser(List<Goods> list);
}
