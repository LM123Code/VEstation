package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.Goods;
import com.zhiyi.vestation.mapper.GoodsMapper;
import com.zhiyi.vestation.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    /**
     * 获取首页商品推荐
     * @return Goods对象列表
     */
    @Override
    public List<Goods> getRecommendGoods() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist", 1); //查询条件包装

        wrapper.orderByDesc("score"); //根据分数倒序

        Page<Goods> page = new Page<>(1,20); //分页规则，第一页，每页20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */
        return baseMapper.selectPage(page, wrapper).getRecords(); //查询并获取记录
    }

    /**
     * 获取去某一页的所有Goods
     * @param p 页码
     * @return 商品列表
     */
    @Override
    public List<Goods> getAllGoodsInPage(int p) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist", 1); //查询条件包装

        wrapper.orderByDesc("createDate"); //根据分数倒序

        Page<Goods> page = new Page<>(p,20); //分页规则，第一页，每页20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */
        return baseMapper.selectPage(page, wrapper).getRecords(); //查询并获取记录
    }
}
