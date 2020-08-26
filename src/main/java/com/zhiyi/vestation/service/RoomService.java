package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Job;
import com.zhiyi.vestation.pojo.ResultStatus;
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
    ResultStatus getRecommendRooms();

    /**
     * 获取去某一页的所有room
     * @param p 页码
     * @return room列表
     */
    ResultStatus getAllRoomsInPage(int p);
    ResultStatus getAllGoodsInPageByPriceIncrease(int p,String keyWorld);
    ResultStatus getAllGoodsInPageByPriceDecrease(int p,String keyWorld);
    ResultStatus getAllGoodsInPageByViewIncrease(int p,String keyWorld);
    ResultStatus getAllGoodsInPageByTimeIncrease(int p,String keyWorld);

    /**
     * 为集合中的租房添加发布者的属性
     * @param list 租房集合
     * @return 较完整的租房集合
     */
    List<Room> addVxUser(List<Room> list);

    /**
     * 通过一个模糊查询从数据库拿到关于room的信息
     * @param key
     * @return
     */
    List<Room> selectRoomListAboutKeyWorlds(String key);
}
