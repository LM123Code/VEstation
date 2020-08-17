package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Goods;
import com.zhiyi.vestation.pojo.Job;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("${api-url}/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;  //注入goods的service对象

    /**
     * 获取首页推荐的商品列表20个
     * @return Goods对象列表
     */
    @ResponseBody
    @GetMapping("/recommendGoods")
    public ResultStatus getRecommendGoods(){
        return goodsService.getRecommendGoods();
    }

    /**
     * 获取去某一页的所有Goods
     * @param p 页码
     * @return 商品列表
     */
    @ResponseBody
    @GetMapping("/allGoods")
    public ResultStatus getAllGoodsInPage(int p){
        return goodsService.getAllGoodsInPage(p);
    }

    /**
     * 根据id获取具体商品
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/goods")
    public Goods getGoodsById(int id){
        return goodsService.getById(id);
    }

    /**
     * 发布或更新商品
     * @param goods 商品对象
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadGoods")
    public ResultStatus uploadGoods(Goods goods){
        boolean b = goodsService.saveOrUpdate(goods);
        return b?new ResultStatus().setCode("200").setMsg("ok"):
                new ResultStatus().setMsg("0").setMsg("更新失败请检查参数");
    }

    /**
     * 传来关键字查询内容
     * @param key 关键字
     * @return
     */
    @PostMapping("/selectGoodsListAboutKeyWorlds")
    public ResultStatus selectGoodsListAboutKeyWorlds(String key){
        List<Goods> list = goodsService.selectGoodsListAboutKeyWorlds(key);
        return new ResultStatus().setMsg("200").setMsg("查询成功").setData(list);
    }
}

