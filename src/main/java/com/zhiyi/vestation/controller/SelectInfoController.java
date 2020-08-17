package com.zhiyi.vestation.controller;

import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.service.ForumService;
import com.zhiyi.vestation.service.GoodsService;
import com.zhiyi.vestation.service.JobService;
import com.zhiyi.vestation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DaHang
 * @date 2020/8/17 15:10
 */
@RestController
@RequestMapping("/select")
public class SelectInfoController {

    @Autowired
    JobService jobService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ForumService forumService;
    @Autowired
    RoomService roomService;

    /**
     * 传来关键字查询所有帖子的内容
     * @param key 关键字
     * @return
     */
    @PostMapping("/selectForumListAboutKeyWorlds")
    public ResultStatus selectForumListAboutKeyWorlds(String key){
        List<Goods> goods = goodsService.selectGoodsListAboutKeyWorlds(key);
        List<Job> jobs = jobService.selectJobListAboutKeyWorlds(key);
        List<Room> rooms = roomService.selectRoomListAboutKeyWorlds(key);
        Map<String,List> map = new HashMap<>();
        map.put("goods",goods);
        map.put("jobs",jobs);
        map.put("rooms",rooms);
        return new ResultStatus().setMsg("200").setMsg("查询成功").setData(map);
    }
}
