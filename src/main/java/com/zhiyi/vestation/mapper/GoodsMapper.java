package com.zhiyi.vestation.mapper;

import com.zhiyi.vestation.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    public List<Goods> selectGoodsByPage(int sizeBegin, int sizeEnd);
}
