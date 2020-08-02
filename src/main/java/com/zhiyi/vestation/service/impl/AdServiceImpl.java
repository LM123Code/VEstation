package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhiyi.vestation.pojo.Ad;
import com.zhiyi.vestation.mapper.AdMapper;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.AdService;
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
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    /**
     * 获取首页的广告
     * @return
     */
    @Override
    public ResultStatus getFirstPageList() {
        QueryWrapper<Ad> wrapper = new QueryWrapper<>();
        wrapper.eq("exist",1);
        /**
         * 应该从ES中查询，上述是从mysql查询
         */
        List<Ad> ads = baseMapper.selectList(wrapper);
        ResultStatus resultStatus = new ResultStatus();
        if (ads == null || ads.size() == 0) {
            return resultStatus.setCode("1").setMsg("没有广告信息");
        }
        return resultStatus.setMsg("ok").setCode("200").setData(ads);
    }
}
