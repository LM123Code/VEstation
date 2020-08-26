package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Job;
import com.zhiyi.vestation.pojo.ResultStatus;
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
    public ResultStatus getRecommendJobs(){
        return jobService.getRecommendJobs();
    }

    /**
     * 获取去某一页的所有job
     * @param p 页码
     * @return job列表
     */
    @ResponseBody
    @GetMapping("/allJobs")
    public ResultStatus getAllJobsInPage(int p){

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
     * 发布或更新职位
     * @param job 职位对象
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadJob")
    public ResultStatus uploadJob(Job job){
        boolean b = jobService.saveOrUpdate(job);
        return b?new ResultStatus().setCode("200").setMsg("更新成功"):
                new ResultStatus().setCode("0").setMsg("更新失败，请检查参数");
    }

    /**
     * 传来关键字查询内容
     * @param key 关键字
     * @return
     */
    @PostMapping("/selectJobsListAboutKeyWorlds")
    public ResultStatus selectJobsListAboutKeyWorlds(String key){
        List<Job> list = jobService.selectJobListAboutKeyWorlds(key);
        return new ResultStatus().setMsg("200").setMsg("查询成功").setData(list);
    }

    /**
     * 根据不同请求返回不同的结果，降序，升序等等
     */
    @PostMapping("/selectJobListAboutKeyWithSomeCondition")
    public ResultStatus selectJobListAboutKeyWithSomeCondition(int flag , int p , String key){
        if (key == null){
            key="";
        }
        ResultStatus resultStatus = null;
        // 1、时间从近至远 2、价格从高到低  3、价格从低到高  4、根据views计算
        if (flag == 1){
            resultStatus = jobService.getAllGoodsInPageByTimeIncrease(p, key);
        }else if (flag == 2){
            resultStatus =jobService.getAllGoodsInPageByPriceDecrease(p,key);
        }else if (flag == 3){
            resultStatus =jobService.getAllGoodsInPageByPriceIncrease(p,key);
        }else if (flag == 4){
            resultStatus =jobService.getAllGoodsInPageByViewIncrease(p,key);
        }
        return resultStatus;
    }
}

