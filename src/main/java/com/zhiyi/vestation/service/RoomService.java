package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Room;
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
public interface RoomService extends IService<Room> {

    /**
     * 获取首页租房信息列表
     * @return room的列表
     */
    List<Room> getRecommendRooms();

    /**
     * 获取去某一页的所有room
     * @param p 页码
     * @return room列表
     */
    List<Room> getAllRoomsInPage(int p);
}
