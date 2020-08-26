package com.zhiyi.vestation.controller;

import com.zhiyi.vestation.pojo.Job;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Room;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
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
@RequestMapping("${api-url}/room")
public class RoomController {

    @Autowired
    RoomService roomService;  //注入room的service对象

    /**
     * 获取首页推荐的租房列表20个
     * @return room对象列表
     */
    @ResponseBody
    @GetMapping("/recommendRooms")
    public ResultStatus getRecommendRooms(){
        return roomService.getRecommendRooms();
    }

    /**
     * 获取去某一页的所有room
     * @param p 页码
     * @return room列表
     */
    @ResponseBody
    @GetMapping("/allRooms")
    public ResultStatus getAllRoomsInPage(int p){
        return roomService.getAllRoomsInPage(p);
    }

    /**
     * 根据id获取具体room
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/room")
    public Room getRoomById(int id){
        return roomService.getById(id);
    }

    /**
     * 发布或更新房子
     * @param room 房子对象
     * @return
     */
    @ResponseBody
    @GetMapping("/uploadRoom")
    public ResultStatus uploadRoom(Room room){
        boolean b = roomService.save(room);
        return b?new ResultStatus().setCode("200").setMsg("ok"):
                new ResultStatus().setCode("1").setMsg("更新失败，请检查参数");
    }

    /**
     * 传来关键字查询内容
     * @param key 关键字
     * @return
     */
    @PostMapping("/selectRoomsListAboutKeyWorlds")
    public ResultStatus selectJobsListAboutKeyWorlds(String key){
        List<Room> list = roomService.selectRoomListAboutKeyWorlds(key);
        return new ResultStatus().setMsg("200").setMsg("查询成功").setData(list);
    }
    /**
     * 根据不同请求返回不同的结果，降序，升序等等
     */
    @PostMapping("/selectRoomListAboutKeyWithSomeCondition")
    public ResultStatus selectRoomListAboutKeyWithSomeCondition(int flag , int p , String key){
        if (key == null){
            key="";
        }
        ResultStatus resultStatus = null;
        // 1、时间从近至远 2、价格从高到低  3、价格从低到高  4、根据views计算
        if (flag == 1){
            resultStatus = roomService.getAllGoodsInPageByTimeIncrease(p, key);
        }else if (flag == 2){
            resultStatus =roomService.getAllGoodsInPageByPriceDecrease(p,key);
        }else if (flag == 3){
            resultStatus =roomService.getAllGoodsInPageByPriceIncrease(p,key);
        }else if (flag == 4){
            resultStatus =roomService.getAllGoodsInPageByViewIncrease(p,key);
        }
        return resultStatus;
    }
}

