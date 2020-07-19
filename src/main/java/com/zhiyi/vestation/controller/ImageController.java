package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.ImageService;
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
@RequestMapping("${api-url}/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    public Status uploadImage(){
        return null;
    }
}

