package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.service.JobService;
import com.zhiyi.vestation.utils.SenInfoCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @Autowired
    HttpSession httpSession;

    @Autowired
    SenInfoCheckUtil senInfoCheckUtil;

    @PostMapping("/addJob")
    public ResultStatus addGood(@RequestBody Job job){
        VxUser sessionVxUser = (VxUser) httpSession.getAttribute("vxUser");
        job.setOpenid(sessionVxUser.getOpenid());
        job.setViews(0);
        job.setCreateDate(new Date());

        /**
         * 这里要对goods中的图片 以及文章的tittle做检测
         */

        boolean b = senInfoCheckUtil.checkMsg(job.getJobTitle());
        if (!b) {
            return ResultStatus.builder().code("709").msg("文本包含敏感数据").build();
        }
        boolean b1 = senInfoCheckUtil.checkMsg(job.getJobDesc());
        if (!b1) {
            return ResultStatus.builder().code("709").msg("文本包含敏感数据").build();
        }
        boolean b2 = senInfoCheckUtil.checkMsg(job.getWechat());
        if (!b2) {
            return ResultStatus.builder().code("709").msg("文本包含敏感数据").build();
        }

        //分割一下
        String jobsUrls = job.getImages();
        String substringUrls = jobsUrls.substring(1, jobsUrls.length() - 1);
        String[] split = substringUrls.split(",");
        for (String url: split) {
            String subUrl = url.substring(1, url.length() - 1);
            boolean b3 = senInfoCheckUtil.checkImg(subUrl);
            if (!b3){
                return ResultStatus.builder().code("708").msg("图片包含敏感数据").build();
            }
        }
        boolean save = jobService.save(job);
        if (save){
            return ResultStatus.builder().code("200").msg("添加成功").build();
        }else {
            return ResultStatus.builder().code("706").msg("添加失败").build();
        }
    }

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

