package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * @param file  图片文件
     * @return 返回status查看状态
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/uploadImage")
    public ResultStatus uploadImage(MultipartFile file) throws IOException {
        System.out.println("上传图片");
        System.out.println(file);
        byte[] image = file.getBytes();
        Map<String, Object> map = imageService.uploadImage(image);
        if ((int)map.get("code") != 200) {
            return ResultStatus.builder().code("0").msg("上传失败").build();
        }
        return ResultStatus.builder().code("200").msg("ok").data(map.get("key")).build();
    }

    



    /**
     * 删除图片
     * @param key 图片名称
     * @return 返回status状态
     */
    @ResponseBody
    @GetMapping("delImage")
    public ResultStatus delImage(String key){
        return imageService.delImage(key);
    }
}

