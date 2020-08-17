package com.zhiyi.vestation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyi.vestation.pojo.Job;
import com.zhiyi.vestation.mapper.JobMapper;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.VxUser;
import com.zhiyi.vestation.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyi.vestation.service.VxUserService;
import org.apache.commons.lang3.StringUtils;
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
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    VxUserService vxUserService;

    @Autowired
    JobMapper jobMapper;

    /**
     * 首页工作推荐
     * @return Job对象列表
     */
    @Override
    public ResultStatus getRecommendJobs() {
        QueryWrapper<Job> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist", 1); //查询条件包装

        wrapper.orderByDesc("score"); //根据分数倒序

        Page<Job> page = new Page<>(1,20); //分页规则，第一页，每页20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */

        List<Job> jobs = addVxUser(baseMapper.selectPage(page, wrapper).getRecords());//查询并获取记录
        ResultStatus resultStatus = new ResultStatus();
        if (jobs == null || jobs.size() == 0) {
            return resultStatus.setCode("'1").setMsg("没有数据");
        }
        return resultStatus.setMsg("ok").setCode("200").setData(jobs);
    }

    /**
     * 获取去某一页的所有job
     * @param p 页码
     * @return job列表
     */
    @Override
    public ResultStatus getAllJobsInPage(int p) {
        QueryWrapper<Job> wrapper = new QueryWrapper<>(); //创建包装
        wrapper.eq("exist", 1); //查询条件包装

        wrapper.orderByDesc("create_date"); //根据分数倒序

        Page<Job> page = new Page<>(p,20); //分页规则，第一页，每页20个

        /**
         * 应该从ES中查询，上述是从mysql查询
         */
        List<Job> jobs = addVxUser(baseMapper.selectPage(page, wrapper).getRecords());//查询并获取记录

        ResultStatus resultStatus = new ResultStatus();
        if (p <= 0) {
            return resultStatus.setMsg("参数异常").setCode("0");
        }else if (jobs == null || jobs.size() == 0) {
            return resultStatus.setCode("1").setMsg("没有数据");
        }
        return resultStatus.setMsg("ok").setCode("200").setData(jobs);
    }

    /**
     * 为集合中的工作添加发布者的属性
     * @param list 工作集合
     * @return 较完整的岗位集合
     */
    @Override
    public List<Job> addVxUser(List<Job> list) {
        HashMap<String, VxUser> map = new HashMap<>(); //存储需要的vxUser
        for (Job job:list) {
            String openid = job.getOpenid(); //获取当前岗位的发布者openid
            VxUser vxUser = map.get(openid); //从map中获取vxUser
            if(vxUser == null){ //不存在就进行查询
                vxUser = vxUserService.selectByWrapper(openid);
                map.put(openid, vxUser); //加入map
            }
            job.setVxUser(vxUser); //为每个岗位设置发布者信息
        }
        return list; //返回岗位列表
    }

    @Override
    public List<Job> selectJobListAboutKeyWorlds(String key) {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(
                wrapper->
                        wrapper.select("exist",String.valueOf(1))
        );
        queryWrapper.or(
                wrapper->
                        wrapper.like("job_class",key)
                                .or().like("job_title",key)
                                .or().like("job_desc",key)
                                .or().like("company_name",key)
                                .or().like("address",key)
                                .or().like("email",key)
                                .or().like("resume_format",key)
                                .or().like("wechat",key)
        );
        List<Job> list = jobMapper.selectList(queryWrapper);
        return list;
    }
}
