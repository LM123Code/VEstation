package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.service.GoodsService;
import com.zhiyi.vestation.utils.SenInfoCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @Autowired
    HttpSession httpSession;

    @Autowired
    SenInfoCheckUtil senInfoCheckUtil;

    /**
     * 获取首页推荐的商品列表20个
     * @return Goods对象列表
     */
    @ResponseBody
    @GetMapping("/recommendGoods")
    public ResultStatus getRecommendGoods(){
        return goodsService.getRecommendGoods();
    }

    @PostMapping("addGood")
    public ResultStatus addGood(@RequestBody Goods goods){
        VxUser sessionVxUser = (VxUser) httpSession.getAttribute("vxUser");
        goods.setOpenid(sessionVxUser.getOpenid());
        goods.setViews(0);
        goods.setCreateDate(new Date());

        /**
         * 这里要对goods中的图片 以及文章的tittle做检测
         */

        boolean b = senInfoCheckUtil.checkMsg(goods.getGoodsTitle());
        if (!b) {
            return ResultStatus.builder().code("709").msg("文本包含敏感数据").build();
        }
        boolean b1 = senInfoCheckUtil.checkMsg(goods.getGoodsDesc());
        if (!b1) {
            return ResultStatus.builder().code("709").msg("文本包含敏感数据").build();
        }
        boolean b2 = senInfoCheckUtil.checkMsg(goods.getContact());
        if (!b2) {
            return ResultStatus.builder().code("709").msg("文本包含敏感数据").build();
        }

        //分割一下
        String goodsUrls = goods.getGoodsUrls();
        String substringUrls = goodsUrls.substring(1, goodsUrls.length() - 1);
        String[] split = substringUrls.split(",");
        for (String url: split) {
            String subUrl = url.substring(1, url.length() - 1);
            boolean b3 = senInfoCheckUtil.checkImg(subUrl);
            if (!b3){
                return ResultStatus.builder().code("708").msg("图片包含敏感数据").build();
            }
        }
        boolean save = goodsService.save(goods);
        if (save){
            return ResultStatus.builder().code("200").msg("添加成功").build();
        }else {
            return ResultStatus.builder().code("706").msg("添加失败").build();
        }
    }

    /**
     * 获取去某一页的所有Goods
     * @param p 页码
     * @return 商品列表
     */
    @ResponseBody
    @GetMapping("/allGoods")
    public ResultStatus getAllGoodsInPage(int p){
        return goodsService.getAllGoodsInPage(p,null);
    }

    @GetMapping("/viewGood")
    public ResultStatus viewGood(int goodId){
        Goods goods = goodsService.getById(goodId);
        goods.setViews(goods.getViews() + 1);
        boolean b = goodsService.updateById(goods);
        if (b) {
            return ResultStatus.builder().code("200").msg("浏览+1").build();
        }else {
            return ResultStatus.builder().code("705").msg("浏览+1失败").build();
        }
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
    public ResultStatus uploadGoods(@RequestBody Goods goods){
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
    /**
     * 根据不同请求返回不同的结果，降序，升序等等
     */
    @PostMapping("/selectGoodsListAboutKeyWithSomeCondition")
    public ResultStatus selectGoodsListAboutKeyWithSomeCondition(int flag , int p , String key){
        if (key == null){
            key="";
        }
        ResultStatus resultStatus = null;
        // 1、时间从近至远 2、价格从高到低  3、价格从低到高  4、根据views计算
        if (flag == 1){
            resultStatus = goodsService.getAllGoodsInPageByTimeIncrease(p, key);
        }else if (flag == 2){
            resultStatus =goodsService.getAllGoodsInPageByPriceDecrease(p,key);
        }else if (flag == 3){
            resultStatus =goodsService.getAllGoodsInPageByPriceIncrease(p,key);
        }else if (flag == 4){
            resultStatus =goodsService.getAllGoodsInPageByViewIncrease(p,key);
        }
        return resultStatus;
    }
}

