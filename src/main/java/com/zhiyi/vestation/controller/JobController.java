package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Job;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("${api-url}/job")
public class JobController {

    @Autowired
    JobService jobService; //注入Job的service对象

    /**
     * 获取首页工作推荐
     * @return Job对象列表
     */
    @ResponseBody
    @GetMapping("/recommendJobs")
    public List<Job> getRecommendJobs(){
        return jobService.getRecommendJobs();
    }

    /**
     * 获取去某一页的所有job
     * @param p 页码
     * @return job列表
     */
    @ResponseBody
    @GetMapping("/allJobs")
    public List<Job> getAllJobsInPage(int p){

        return jobService.getAllJobsInPage(p);
    }

    /**
     * 根据id获取具体job
     * @param id
     * @return
     */

    @ResponseBody
    @GetMapping("/job")
    public Job getJobById(int id){
        return jobService.getById(id);
    }

    /**
     * 发布职位
     * @param job 职位对象
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadJob")
    public Status uploadJob(Job job){
        boolean code = jobService.save(job);
        return new Status(code==true?200:0, "发布职位成功");
    }
}

