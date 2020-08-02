package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyi.vestation.pojo.ResultStatus;

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
    ResultStatus getRecommendJobs();

    /**
     * 获取去某一页的所有job
     * @param p 页码
     * @return job列表
     */
    ResultStatus getAllJobsInPage(int p);

    /**
     * 为集合中的工作添加发布者的属性
     * @param list 工作集合
     * @return 较完整的岗位集合
     */
    List<Job> addVxUser(List<Job> list);
}
