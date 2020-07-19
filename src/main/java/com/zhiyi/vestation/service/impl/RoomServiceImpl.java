package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.Room;
import com.zhiyi.vestation.mapper.RoomMapper;
import com.zhiyi.vestation.service.RoomService;
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
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    /**
     * 获取首页租房信息列表
     * @return room的列表
     */
    @Override
    public List<Room> getRecommendRooms() {
        QueryWrapper<Room> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist",1); //查询条件包装

        wrapper.orderByDesc("score"); //根据分数排序

        Page<Room> page = new Page<>(1, 20); //查询前20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */

        return baseMapper.selectPage(page, wrapper).getRecords(); //查询并获取记录
    }

    /**
     * 获取去某一页的所有room
     * @param p 页码
     * @return room列表
     */
    @Override
    public List<Room> getAllRoomsInPage(int p) {

        QueryWrapper<Room> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist", 1); //查询条件包装

        wrapper.orderByDesc("createDate"); //根据分数倒序

        Page<Room> page = new Page<>(p,20); //分页规则，第一页，每页20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */
        return baseMapper.selectPage(page, wrapper).getRecords(); //查询并获取记录
    }
}
