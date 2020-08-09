package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Room;
import com.zhiyi.vestation.mapper.RoomMapper;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.service.VxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Autowired
    VxUserService vxUserService;

    /**
     * 获取首页租房信息列表
     * @return room的列表
     */
    @Override
    public ResultStatus getRecommendRooms() {
        QueryWrapper<Room> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist",1); //查询条件包装

        wrapper.orderByDesc("score"); //根据分数排序

        Page<Room> page = new Page<>(1, 20); //查询前20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */

        List<Room> rooms = addVxUser(baseMapper.selectPage(page, wrapper).getRecords());//查询并获取记录
        ResultStatus resultStatus = new ResultStatus();
        if (rooms == null || rooms.size() == 0) {
            return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setCode("200").setData(rooms).setMsg("ok");

    }

    /**
     * 获取去某一页的所有room
     * @param p 页码
     * @return room列表
     */
    @Override
    public ResultStatus getAllRoomsInPage(int p) {

        QueryWrapper<Room> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist", 1); //查询条件包装

        wrapper.orderByDesc("create_date"); //根据分数倒序

        Page<Room> page = new Page<>(p,20); //分页规则，第一页，每页20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */
        List<Room> rooms = addVxUser(baseMapper.selectPage(page, wrapper).getRecords());//查询并获取记录
        ResultStatus resultStatus = new ResultStatus();
        if (p <= 0) {
            return resultStatus.setMsg("参数异常").setCode("0");
        }else if (rooms == null || rooms.size() ==0) {
            return resultStatus.setMsg("没有数据").setCode("1");
        }
        return resultStatus.setCode("200").setMsg("ok").setData(rooms);
    }

    /**
     * 为集合中的租房添加发布者的属性
     * @param list 租房集合
     * @return 较完整的租房集合
     */
    @Override
    public List<Room> addVxUser(List<Room> list) {
        HashMap<String, VxUser> map = new HashMap<>(); //存储需要的vxUser
        for (Room room:list) {
            String openid = room.getOpenid(); //获取当前room的发布者openid
            VxUser vxUser = map.get(openid); //从map中获取vxUser
            if(vxUser == null){ //不存在就进行查询
                vxUser = vxUserService.selectByWrapper(openid);
                map.put(openid, vxUser); //加入map
            }
            room.setVxUser(vxUser); //为每个room设置发布者信息
        }
        return list; //返回room列表
    }
}
