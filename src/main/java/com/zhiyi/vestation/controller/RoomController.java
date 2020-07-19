package com.zhiyi.vestation.controller;

import com.zhiyi.vestation.pojo.Room;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.RoomService;
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
    public List<Room> getRecommendRooms(){
        return roomService.getRecommendRooms();
    }

    /**
     * 获取去某一页的所有room
     * @param p 页码
     * @return room列表
     */
    @ResponseBody
    @GetMapping("/allRooms")
    public List<Room> getAllRoomsInPage(int p){

        return roomService.getAllRoomsInPage(p);
    }

    /**
     * 根据id获取具体room
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/room")
    public Room getRoomById(int id){
        return roomService.getById(id);
    }

    /**
     * 发布房子
     * @param room 房子对象
     * @return
     */
    @ResponseBody
    @GetMapping("/uploadRoom")
    public Status uploadRoom(Room room){
        boolean code = roomService.save(room);
        return new Status(code==true?200:0, "发布商品成功");
    }
}

