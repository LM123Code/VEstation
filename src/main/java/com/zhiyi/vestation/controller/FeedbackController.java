package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Feedback;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@RestController
@RequestMapping("${api-url}$/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    public ResultStatus feedback(Feedback feedback) {
        int insert = feedbackService.insertFeedback(feedback);
        if (feedback == null) {
            return ResultStatus.builder().code("0").msg("参数异常").build();
        } else if (insert <= 0) {
            return ResultStatus.builder().code("1").msg("反馈失败").build();
        } else {
            return ResultStatus.builder().code("200").msg("ok").build();
        }
    }
}

