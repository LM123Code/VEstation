package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    /**
     * 上传图片
     * @param multipartFile  图片文件
     * @return 返回status查看状态
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("uploadImage")
    public Status uploadImage(MultipartFile multipartFile) throws IOException {
        byte[] image = multipartFile.getBytes();
        return imageService.uploadImage(image);
    }

    /**
     * 删除图片
     * @param key 图片名称
     * @return 返回status状态
     */
    @ResponseBody
    @GetMapping("delImage")
    public Status delImage(String key){
        return imageService.delImage(key);
    }
}

