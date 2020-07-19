package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Job;
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
public interface JobService extends IService<Job> {

    /**
     * 首页工作推荐
     * @return Job对象列表
     */
    List<Job> getRecommendJobs();

    /**
     * 获取去某一页的所有job
     * @param p 页码
     * @return job列表
     */
    List<Job> getAllJobsInPage(int p);
}
